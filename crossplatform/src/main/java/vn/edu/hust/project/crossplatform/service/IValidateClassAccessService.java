package vn.edu.hust.project.crossplatform.service;

import vn.edu.hust.project.crossplatform.dto.ClassDto;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;

public interface IValidateClassAccessService {
    boolean canEditClass(Account account, String classCode);
    ClassDto getAndCheckEditClass(Account account, String classCode);
    void checkEditClass(Account account, String classId);
    void checkAccessClassInfo(ClassDto classDto, Account account);
    ClassDto checkAccessClassInfo(String classCode, String token);
    boolean isStudentBelongToClass(Integer studentId, Integer classId);
    ClassDto checkStudentBelongToClass(Integer studentId, String classCode);
}
