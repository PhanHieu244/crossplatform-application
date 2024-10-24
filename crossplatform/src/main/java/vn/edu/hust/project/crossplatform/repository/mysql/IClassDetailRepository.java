package vn.edu.hust.project.crossplatform.repository.mysql;

import org.springframework.stereotype.Repository;
import vn.edu.hust.project.crossplatform.repository.mysql.model.ClassDetailModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface IClassDetailRepository extends IBaseRepository<ClassDetailModel>{
    List<ClassDetailModel> findByClassId(Integer classId);
    List<ClassDetailModel> findAllByStudentId(Integer studentId);
    Optional<ClassDetailModel> findByStudentIdAndClassId(Integer studentId, Integer classId);
    Integer countByClassId(Integer classId);
}
