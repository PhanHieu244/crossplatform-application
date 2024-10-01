package vn.edu.hust.project.crossplatform.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.port.IAuthPort;
import vn.edu.hust.project.crossplatform.port.ILecturerPort;
import vn.edu.hust.project.crossplatform.port.IStudentPort;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Lecturer;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Student;
import vn.edu.hust.project.crossplatform.service.IAuthService;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final IAuthPort authPort;
    private final ILecturerPort lecturerPort;
    private final IStudentPort studentPort;

    @Override
    public Account getAccountByToken(String token) {
        return authPort.getAccountByToken(token);
    }

    @Override
    public void checkRole(Account account, Account.Role role) {
        authPort.checkRole(account, role);
    }

    @Override
    public void checkRole(Account account, Account.Role... roles) {
        authPort.checkRole(account, roles);
    }

    @Override
    public Account getAccountAndCheckRole(String token, Account.Role role) {
        var account = getAccountByToken(token);
        checkRole(account, role);
        return account;
    }

    @Override
    public Boolean hasRole(String token, Account.Role role) {
        var account = getAccountByToken(token);
        return account.getRole().equals(role.toString());
    }

    public Lecturer getLecturerByToken(String token) {
        var account = getAccountByToken(token);
        return getLecturerByAccount(account);
    }

    public Lecturer getLecturerByAccount(Account account) {
        checkRole(account, Account.Role.LECTURER);
        return lecturerPort.getLecturer(account);
    }

    public Student getStudentByToken(String token) {
        var account = getAccountByToken(token);
        return getStudentByAccount(account);
    }

    public Student getStudentByAccount(Account account) {
        checkRole(account, Account.Role.STUDENT);
        return studentPort.getStudent(account);
    }

}
