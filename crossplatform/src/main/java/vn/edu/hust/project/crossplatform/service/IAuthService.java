package vn.edu.hust.project.crossplatform.service;

import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Lecturer;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Student;

public interface IAuthService {
    Account getAccountByToken(String token);
    void checkRole(Account account, Account.Role role);
    void checkRole(Account account, Account.Role... roles);
    void checkRole(String token, Account.Role role);
    Account getAccountAndCheckRole(String token, Account.Role role);
    Boolean hasRole(String token, Account.Role role);
    Lecturer getLecturerByToken(String token);
    Lecturer getLecturerByAccount(Account account);
    Student getStudentByToken(String token);
    Student getStudentByAccount(Account account);
}
