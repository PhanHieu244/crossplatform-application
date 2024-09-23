package vn.edu.hust.project.crossplatform.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Course;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Lecturer;

import java.util.List;

public interface ClassRepository extends JpaRepository<Course, Long> {
    boolean existsByClassName(String class_name);
    List<Course> findByLecturer(Lecturer lecturerId);
}
