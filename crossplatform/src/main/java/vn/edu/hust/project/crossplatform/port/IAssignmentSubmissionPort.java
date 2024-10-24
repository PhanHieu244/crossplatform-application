package vn.edu.hust.project.crossplatform.port;

import org.springframework.web.multipart.MultipartFile;
import vn.edu.hust.project.crossplatform.dto.AssignmentSubmissionDto;
import vn.edu.hust.project.crossplatform.dto.request.GetAssignSubmissionsRequest;
import vn.edu.hust.project.crossplatform.dto.request.GradeSubmission;
import vn.edu.hust.project.crossplatform.dto.response.SubmitAssignmentResponse;

import java.util.List;

public interface IAssignmentSubmissionPort {
    List<AssignmentSubmissionDto> getAllSubmissions(Integer assignmentId);
    AssignmentSubmissionDto getSubmission(Integer assignmentId, Integer studentId);
    Boolean existSubmission(Integer assignmentId, Integer studentId);
    SubmitAssignmentResponse submitAssignment(AssignmentSubmissionDto entity, MultipartFile file);
    List<AssignmentSubmissionDto> responseSubmission(GetAssignSubmissionsRequest request);
    void responseSubmission(GradeSubmission gradeSubmission);
}
