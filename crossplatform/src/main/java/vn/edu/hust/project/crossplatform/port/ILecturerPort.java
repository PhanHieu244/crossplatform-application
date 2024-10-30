package vn.edu.hust.project.crossplatform.port;

import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Lecturer;

public interface ILecturerPort {
    Lecturer getLecturer(Account account);
    Lecturer getLecturer(Integer id);
    String getLecturerName(Integer id);
}
