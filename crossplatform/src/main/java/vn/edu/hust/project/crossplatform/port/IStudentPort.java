package vn.edu.hust.project.crossplatform.port;

import vn.edu.hust.project.crossplatform.dto.StudentDto;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;

public interface IStudentPort {
    StudentDto getStudent(Account account);
    StudentDto findStudentById(Integer id);
}
