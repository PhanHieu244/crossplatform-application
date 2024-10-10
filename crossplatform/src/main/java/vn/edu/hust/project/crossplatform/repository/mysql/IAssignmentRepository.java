package vn.edu.hust.project.crossplatform.repository.mysql;

import org.springframework.stereotype.Repository;
import vn.edu.hust.project.crossplatform.repository.mysql.model.AssignmentModel;

import java.util.List;

@Repository
public interface IAssignmentRepository extends IBaseRepository<AssignmentModel> {
    List<AssignmentModel> findAllByClassId(Integer classId);
}
