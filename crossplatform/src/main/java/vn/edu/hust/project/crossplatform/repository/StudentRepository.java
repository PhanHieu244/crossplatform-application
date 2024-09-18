package vn.edu.hust.project.crossplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hust.project.crossplatform.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}