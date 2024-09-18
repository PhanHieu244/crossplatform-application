package vn.edu.hust.project.crossplatform.service;

import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.entity.Student;
import vn.edu.hust.project.crossplatform.repository.StudentRepository;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }
}
