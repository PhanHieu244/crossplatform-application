package vn.edu.hust.project.crossplatform.repository.mysql;

import org.springframework.stereotype.Repository;
import vn.edu.hust.project.crossplatform.repository.mysql.model.AssignmentSubmissionModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAssignmentSubmissionRepository extends IBaseRepository<AssignmentSubmissionModel> {
    boolean existsByAssignmentId(Integer assignmentId);
    boolean existsByAssignmentIdAndStudentId(Integer assignmentId, Integer studentId);
    List<AssignmentSubmissionModel> findAllByAssignmentId(Integer assignmentId);
    Optional<AssignmentSubmissionModel> findByAssignmentIdAndStudentId(Integer assignmentId, Integer studentId);
}
