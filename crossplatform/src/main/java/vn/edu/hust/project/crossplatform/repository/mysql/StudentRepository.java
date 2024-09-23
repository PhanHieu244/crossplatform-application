package vn.edu.hust.project.crossplatform.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Course;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Long countByClassField(Course class_id);
}