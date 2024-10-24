package vn.edu.hust.project.crossplatform.port;

import org.springframework.web.multipart.MultipartFile;
import vn.edu.hust.project.crossplatform.dto.AssignmentDto;
import vn.edu.hust.project.crossplatform.repository.mysql.model.AssignmentModel;

import java.util.List;
import java.util.function.Predicate;

public interface IAssignmentPort {
    AssignmentDto createAssignment(AssignmentDto assignment, MultipartFile file);
    AssignmentDto editAssignment(AssignmentDto assignment, MultipartFile file, Predicate<AssignmentModel> editPredicate);
    void deleteAssignment(Integer assignmentId);
    List<AssignmentDto> getAllAssignments(Integer classId);
    AssignmentModel getAssignmentById(Integer assignmentId);
}
