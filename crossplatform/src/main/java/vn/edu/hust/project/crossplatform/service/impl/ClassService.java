package vn.edu.hust.project.crossplatform.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import vn.edu.hust.project.crossplatform.constant.ClassStatus;
import vn.edu.hust.project.crossplatform.constant.ResponseCode;
import vn.edu.hust.project.crossplatform.dto.ClassDto;
import vn.edu.hust.project.crossplatform.dto.mapper.ClassDtoMapper;
import vn.edu.hust.project.crossplatform.dto.request.CreateClassRequest;
import vn.edu.hust.project.crossplatform.dto.request.EditClassRequest;
import vn.edu.hust.project.crossplatform.dto.response.ClassInfoResponse;
import vn.edu.hust.project.crossplatform.exception.UnauthorizedException;
import vn.edu.hust.project.crossplatform.exception.base.ApplicationException;
import vn.edu.hust.project.crossplatform.port.IClassDetailPort;
import vn.edu.hust.project.crossplatform.port.IClassPort;
import vn.edu.hust.project.crossplatform.port.ILecturerPort;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;
import vn.edu.hust.project.crossplatform.service.IAuthService;
import vn.edu.hust.project.crossplatform.service.IClassService;
import vn.edu.hust.project.crossplatform.utils.ClassUtils;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ClassService implements IClassService {
    private final IClassPort classPort;
    private final IAuthService authService;
    private final ILecturerPort lecturerPort;
    private final IClassDetailPort classDetailPort;

    @Override
    public ClassDto createClass(CreateClassRequest request) {
        var lecturer = lecturerPort.getLecturer(request.getLecturerId());
        if(lecturer == null) {
            throw new ApplicationException(ResponseCode.PARAMETER_VALUE_IS_INVALID, "not exist this lecturer id");
        }
        return classPort.createClass(ClassDtoMapper.INSTANCE.toClassDto(request));
    }

    @Override
    public ClassDto editClass(EditClassRequest request) {
        var account = authService.getAccountByToken(request.getToken());
        checkEditClass(account, request.getClassId());
        return classPort.editClass(request);
    }

    @Override
    public void deleteClass(String code) {
        classPort.deleteClass(code);
    }

    @Override
    public void deleteClass(String code, Account account) {
        checkEditClass(account, code);
        classPort.deleteClass(code);
    }

    @Override
    public ClassDto getClassByCode(String code) {
        return classPort.findClassByCode(code);
    }

    @Override
    public ClassDto getClassByCode(String code, Account account) {
        ClassDto classDto = classPort.findClassByCode(code);
        checkAccessClassInfo(classDto, account);
        return classDto;
    }

    @Override
    public List<ClassInfoResponse> getClassList(Account account) {
        List<ClassDto> classList;
        if(account.getRole().equals(Account.Role.LECTURER.toString())){
            var lecturerId = authService.getLecturerByAccount(account).getId();
            classList = classPort.getLecturerClasses(lecturerId);
        }
        else if(account.getRole().equals(Account.Role.LECTURER.toString())){
            var lecturerId = authService.getLecturerByAccount(account).getId();
            classList = classPort.getStudentClasses(lecturerId);
        }
        else{
            log.error("your role cant access class list");
            throw new UnauthorizedException("your role cant access class list");
        }
        if(CollectionUtils.isEmpty(classList)) return List.of();

        return classList.stream()
                .map(this::toClassResponse)
                .toList();
    }

    public ClassInfoResponse toClassResponse(ClassDto classDto) {
        var classInfoResponse = new ClassInfoResponse();
        classInfoResponse.setClassId(classDto.getClassId());
        classInfoResponse.setClassName(classDto.getClassName());;
        classInfoResponse.setLecturerName(lecturerPort.getLecturerName(classDto.getLecturerId()));
        classInfoResponse.setStudentCount(classDetailPort.getStudentCount(classDto.getId()));
        classInfoResponse.setStatus(classDto.getStatus());
        classInfoResponse.setEndDate(classDto.getEndDate());
        classInfoResponse.setStartDate(classDto.getStartDate());
        return classInfoResponse;
    }

    private void checkEditClass(Account account, String classCode){
        var classDto = classPort.findClassByCode(classCode);
        if(!checkLecturerAccess(account, classDto)){
            log.error("your role can't edit this class");
            throw new UnauthorizedException("your role can't edit this class");
        }
    }

    private void checkAccessClassInfo(ClassDto classDto, Account account) {
        if(checkLecturerAccess(account, classDto)){
            return;
        }
        else if(account.getRole().equals(Account.Role.STUDENT.toString())) {
            var studentId = authService.getStudentByAccount(account).getId();
            if(!studentCanAccessClassInfo(classDto, studentId)){
                log.warn("student can not access class");
                throw new UnauthorizedException("student can not access class");
            }
            return;
        }

        log.error("your role can't access this class");
        throw new UnauthorizedException("your role can't access this class");
    }

    private boolean checkLecturerAccess(Account account, ClassDto classDto) {
        if(account.getRole().equals(Account.Role.LECTURER.toString())){
            var lecturerId = authService.getLecturerByAccount(account).getId();
            if(!lecturerCanAccessClassInfo(classDto, lecturerId)){
                log.warn("lecturer can not access other lecturer's class");
                throw new UnauthorizedException("lecturer can not access other lecturer's class");
            }
            return true;
        }
        else {
            return false;
        }
    }

    private Boolean lecturerCanAccessClassInfo(ClassDto classDto, Integer lectureId){
        return classDto.getLecturerId().equals(lectureId);
    }
    private Boolean studentCanAccessClassInfo(ClassDto classDto, Integer studentId){
        return false;
    }

}
