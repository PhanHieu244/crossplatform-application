package vn.edu.hust.project.crossplatform.repository.mysql.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.exception.NoDataException;
import vn.edu.hust.project.crossplatform.port.ILecturerPort;
import vn.edu.hust.project.crossplatform.repository.mysql.LecturerRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Lecturer;

@Service
@RequiredArgsConstructor
public class LecturerAdapter implements ILecturerPort {
    private final LecturerRepository lecturerRepository;

    @Override
    public Lecturer getLecturer(Account account) {
        var lecturer = lecturerRepository.findByAccount(account);
        if(lecturer == null){
            throw new NoDataException("Lecturer not found");
        }
        return lecturer;
    }

    @Override
    public Lecturer getLecturer(Integer id) {
        return lecturerRepository.findById(id.longValue()).orElseThrow(
                () -> new NoDataException("Lecturer not found")
        );
    }

    @Override
    public String getLecturerName(Integer id) {
        return getLecturer(id).getName();
    }
}
