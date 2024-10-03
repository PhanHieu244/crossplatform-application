package vn.edu.hust.project.crossplatform.port;

import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Student;

public interface IStudentPort {
    Student getStudent(Account account);
    Student findStudentById(Integer id);
}
