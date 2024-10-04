package vn.edu.hust.project.crossplatform.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.dto.ClassDto;
import vn.edu.hust.project.crossplatform.exception.UnauthorizedException;
import vn.edu.hust.project.crossplatform.port.IClassDetailPort;
import vn.edu.hust.project.crossplatform.port.IClassPort;
import vn.edu.hust.project.crossplatform.port.ILecturerPort;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;
import vn.edu.hust.project.crossplatform.service.IAuthService;
import vn.edu.hust.project.crossplatform.service.IValidateClassAccessService;

@Service
@Slf4j
@RequiredArgsConstructor
public class ValidateClassAccessService implements IValidateClassAccessService {
    private final IClassPort classPort;
    private final IAuthService authService;
    private final ILecturerPort lecturerPort;
    private final IClassDetailPort classDetailPort;

    @Override
    public boolean canEditClass(Account account, String classCode) {
        var classDto = classPort.findClassByCode(classCode);
        return checkLecturerAccess(account, classDto);
    }

    @Override
    public ClassDto getAndCheckEditClass(Account account, String classCode) {
        checkEditClass(account, classCode);
        return classPort.findClassByCode(classCode);
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

    public void checkEditClass(Account account, String classCode){
        if(!canEditClass(account, classCode)){
            log.error("your role can't edit this class");
            throw new UnauthorizedException("your role can't edit this class");
        }
    }

    public void checkAccessClassInfo(ClassDto classDto, Account account) {
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

    @Override
    public ClassDto checkAccessClassInfo(String classCode, String token) {
        var classDto = classPort.findClassByCode(classCode);
        var account = authService.getAccountByToken(token);
        checkAccessClassInfo(classDto, account);
        return classDto;
    }

    @Override
    public boolean isStudentBelongToClass(Integer studentId, Integer classId) {
        return classDetailPort.isStudentBelongToClass(classId, studentId);
    }

    @Override
    public ClassDto checkStudentBelongToClass(Integer studentId, String classCode) {
        var classDto = classPort.findClassByCode(classCode);
        if(!isStudentBelongToClass(studentId, classDto.getId())){
            log.warn("student not belong to class");
            throw new UnauthorizedException("student not belong to class");
        }
        return classDto;
    }

    private Boolean lecturerCanAccessClassInfo(ClassDto classDto, Integer lectureId){
        return classDto.getLecturerId().equals(lectureId);
    }
    private Boolean studentCanAccessClassInfo(ClassDto classDto, Integer studentId){
        return isStudentBelongToClass(classDto.getId(), studentId);
    }
}
