package vn.edu.hust.project.crossplatform.repository.mysql.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.exception.NoDataException;
import vn.edu.hust.project.crossplatform.port.IStudentPort;
import vn.edu.hust.project.crossplatform.repository.mysql.StudentRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Student;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentAdapter implements IStudentPort{
    private final StudentRepository studentRepository;

    @Override
    public Student getStudent(Account account) {
        return studentRepository.findStudentByAccount(account).orElseThrow(
                () ->{
                    log.error("no student found for account {}", account);
                    return new NoDataException("student not found");
                }
        );
    }

    @Override
    public Student findStudentById(Integer id) {
        return studentRepository.findById(id.longValue()).orElseThrow(
                () -> {
                    log.error("no student found for id {}", id);
                    return new NoDataException("student not found");
                }
        );
    }
}
