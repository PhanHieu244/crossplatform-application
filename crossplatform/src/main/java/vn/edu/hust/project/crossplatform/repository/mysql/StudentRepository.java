package vn.edu.hust.project.crossplatform.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Student;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findStudentByAccount(Account account);
}