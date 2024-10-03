package vn.edu.hust.project.crossplatform.repository.mysql.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.constant.ResponseCode;
import vn.edu.hust.project.crossplatform.exception.base.ApplicationException;
import vn.edu.hust.project.crossplatform.port.IAuthPort;
import vn.edu.hust.project.crossplatform.port.IClassDetailPort;
import vn.edu.hust.project.crossplatform.repository.mysql.IClassDetailRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.IClassRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.model.ClassDetailModel;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClassDetailAdapter implements IClassDetailPort {
    private final IClassDetailRepository classDetailRepository;
    private final IClassRepository classRepository;

    @Override
    public Integer getStudentCount(Integer classId) {
        return classDetailRepository.countByClassId(classId);
    }

    @Override
    public void addStudent(Integer classId, Integer studentId) {
        try {
            var newClassDetail = new ClassDetailModel();
            newClassDetail.setClassId(classId);
            newClassDetail.setStudentId(studentId);
            classDetailRepository.save(newClassDetail);
        }
        catch (Exception e) {
            log.error("add student fail, err: {}", e.getMessage());
            throw new ApplicationException(ResponseCode.EXCEPTION_ERROR ,"add student fail");
        }
    }

}
