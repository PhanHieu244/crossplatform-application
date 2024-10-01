package vn.edu.hust.project.crossplatform.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import vn.edu.hust.project.crossplatform.constant.ClassStatus;
import vn.edu.hust.project.crossplatform.dto.ClassDto;
import vn.edu.hust.project.crossplatform.dto.mapper.ClassDtoMapper;
import vn.edu.hust.project.crossplatform.dto.request.CreateClassRequest;
import vn.edu.hust.project.crossplatform.dto.request.EditClassRequest;
import vn.edu.hust.project.crossplatform.dto.response.ClassInfoResponse;
import vn.edu.hust.project.crossplatform.exception.UnauthorizedException;
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
    public ClassDto createClass(CreateClassRequest request, Integer lecturerId) {
        request.setLecturerId(lecturerId);
        return classPort.createClass(ClassDtoMapper.INSTANCE.toClassDto(request));
    }

    @Override
    public ClassDto editClass(EditClassRequest request) {
        return classPort.editClass(request);
    }

    @Override
    public void deleteClass(Integer classId) {
        classPort.deleteClass(classId);
    }

    @Override
    public void deleteClass(Integer classId, Integer lectureId) {
        classPort.deleteClass(classId, lectureId);
    }

    @Override
    public ClassDto getClassById(Integer classId) {
        return classPort.findClassById(classId);
    }

    @Override
    public ClassDto getClassById(Integer classId, Account account) {
        ClassDto classDto = classPort.findClassById(classId);
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
            throw new UnauthorizedException();
        }
        if(CollectionUtils.isEmpty(classList)) return List.of();

        return classList.stream()
                .map(this::toClassResponse)
                .toList();
    }

    public ClassInfoResponse toClassResponse(ClassDto classDto) {
        var classInfoResponse = new ClassInfoResponse();
        classInfoResponse.setClassId(classDto.getCode());
        classInfoResponse.setClassName(classDto.getClassName());;
        classInfoResponse.setLecturerName(lecturerPort.getLecturerName(classDto.getLectureId()));
        classInfoResponse.setStudentCount(classDetailPort.getStudentCount(classDto.getId()));
        classInfoResponse.setStatus(classDto.getStatus());
        classInfoResponse.setEndDate(classDto.getEndDate());
        classInfoResponse.setStartDate(classDto.getStartDate());
        return classInfoResponse;
    }



    private void checkAccessClassInfo(ClassDto classDto, Account account) {
        if(account.getRole().equals(Account.Role.LECTURER.toString())){
            var lecturerId = authService.getLecturerByAccount(account).getId();
            if(!lecturerCanAccessClassInfo(classDto, lecturerId)){

                log.warn("lecturer can not access other lecturer's class");
                throw new UnauthorizedException("lecturer can not access other lecturer's class");
            }
            return;
        }

        if(account.getRole().equals(Account.Role.STUDENT.toString())) {
            var studentId = authService.getStudentByAccount(account).getId();
            if(!studentCanAccessClassInfo(classDto, studentId)){
                log.warn("student can not access class");
                throw new UnauthorizedException("student can not access class");
            }
            return;
        }

        throw new UnauthorizedException();
    }

    private Boolean lecturerCanAccessClassInfo(ClassDto classDto, Integer lectureId){
        return classDto.getLectureId().equals(lectureId);
    }
    private Boolean studentCanAccessClassInfo(ClassDto classDto, Integer studentId){
        return false;
    }

}
