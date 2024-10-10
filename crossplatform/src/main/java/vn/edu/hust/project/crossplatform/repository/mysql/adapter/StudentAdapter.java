package vn.edu.hust.project.crossplatform.repository.mysql.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.dto.StudentDto;
import vn.edu.hust.project.crossplatform.exception.NoDataException;
import vn.edu.hust.project.crossplatform.port.IStudentPort;
import vn.edu.hust.project.crossplatform.repository.mysql.StudentRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.mapper.StudentModelMapper;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentAdapter implements IStudentPort{
    private final StudentRepository studentRepository;

    @Override
    public StudentDto getStudent(Account account) {
        return StudentModelMapper.INSTANCE.toEntity(
                studentRepository.findStudentByAccount(account).orElseThrow(
                        () ->{
                            log.error("no student found for account {}", account);
                            return new NoDataException("student not found");
                        }
                )
        );
    }

    @Override
    public StudentDto findStudentById(Integer id) {
        return StudentModelMapper.INSTANCE.toEntity(
                studentRepository.findById(id.longValue()).orElseThrow(
                        () -> {
                            log.error("no student found for id {}", id);
                            return new NoDataException("student not found");
                        }
                )
        );
    }
}
