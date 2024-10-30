package vn.edu.hust.project.crossplatform.service;

import vn.edu.hust.project.crossplatform.dto.ClassDto;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Lecturer;

public interface IValidateClassAccessService {
    boolean canEditClass(Account account, String classCode);
    ClassDto getAndCheckEditClass(Account account, String classCode);
    ClassDto getAndCheckEditClass(Lecturer lecturer, String classCode);
    void checkEditClass(Account account, String classCode);
    void checkAccessClassInfo(ClassDto classDto, Account account);
    void checkAccessClassInfo(String classCode, String token);
    ClassDto getAndCheckAccessClassInfo(String classCode, String token);
    boolean isStudentBelongToClass(Integer studentId, Integer classId);
    ClassDto checkStudentBelongToClass(Integer studentId, String classCode);

    void checkLecturerAccessClassInfo(ClassDto classDto, Integer lecturerId);
}
