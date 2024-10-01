package vn.edu.hust.project.crossplatform.repository.mysql.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.port.IClassDetailPort;
import vn.edu.hust.project.crossplatform.repository.mysql.IClassDetailRepository;

@Service
@RequiredArgsConstructor
public class ClassDetailAdapter implements IClassDetailPort {
    private final IClassDetailRepository classDetailRepository;

    @Override
    public Integer getStudentCount(Integer classId) {
        return classDetailRepository.countByClassId(classId);
    }
}
