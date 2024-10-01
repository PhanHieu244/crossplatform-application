package vn.edu.hust.project.crossplatform.repository.mysql.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.exception.NoDataException;
import vn.edu.hust.project.crossplatform.port.IStudentPort;
import vn.edu.hust.project.crossplatform.repository.mysql.StudentRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Student;

@Service
@RequiredArgsConstructor
public class StudentAdapter implements IStudentPort{
    private final StudentRepository studentRepository;

    @Override
    public Student getStudent(Account account) {
        return studentRepository.findStudentByAccount(account).orElseThrow(
                () -> new NoDataException("student not found")
        );
    }
}
