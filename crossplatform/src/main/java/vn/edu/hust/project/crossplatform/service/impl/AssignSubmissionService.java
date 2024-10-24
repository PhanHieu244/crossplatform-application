package vn.edu.hust.project.crossplatform.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.constant.ResponseCode;
import vn.edu.hust.project.crossplatform.dto.AssignmentSubmissionDto;
import vn.edu.hust.project.crossplatform.dto.request.GetAssignSubmissionsRequest;
import vn.edu.hust.project.crossplatform.dto.request.SubmitAssignmentRequest;
import vn.edu.hust.project.crossplatform.dto.response.SubmitAssignmentResponse;
import vn.edu.hust.project.crossplatform.exception.UnauthorizedException;
import vn.edu.hust.project.crossplatform.exception.base.ApplicationException;
import vn.edu.hust.project.crossplatform.mapper.AssignSubmissionMapper;
import vn.edu.hust.project.crossplatform.port.IAssignmentPort;
import vn.edu.hust.project.crossplatform.port.IAssignmentSubmissionPort;
import vn.edu.hust.project.crossplatform.port.IClassPort;
import vn.edu.hust.project.crossplatform.service.IAssignSubmissionService;
import vn.edu.hust.project.crossplatform.service.IAuthService;
import vn.edu.hust.project.crossplatform.service.IValidateClassAccessService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssignSubmissionService implements IAssignSubmissionService {
    private final IAssignmentSubmissionPort submissionPort;
    private final IAssignmentPort assignmentPort;
    private final IClassPort classPort;
    private final IValidateClassAccessService validateClassAccessService;
    private final IAuthService authService;

    public SubmitAssignmentResponse submitAssignment(SubmitAssignmentRequest request) {
        var student = authService.getStudentByToken(request.getToken());
        var assignmentEntity = assignmentPort.getAssignmentById(request.getAssignmentId());
        var classEntity = classPort.findClassById(assignmentEntity.getClassId());
        if(LocalDateTime.now().isAfter(assignmentEntity.getDeadline())){
            log.error("cant submit because deadline of assignment has pass");
            throw new ApplicationException("cant submit because deadline of assignment has pass");
        }
        if(!validateClassAccessService.isStudentBelongToClass(student.getId(), classEntity.getId())){
            log.error("student not allowed to submit assignment");
            throw new UnauthorizedException("student not allowed to submit assignment");
        }
        if(submissionPort.existSubmission(assignmentEntity.getId(), student.getId())){
            log.error("student can only submit an assignment once");
            throw new ApplicationException(
                    ResponseCode.ACTION_HAS_BEEN_DONE_PREVIOUSLY_BY_THIS_USER,
                    "student can only submit an assignment once"
            );
        }
        var submission = AssignSubmissionMapper.INSTANCE.requestToEntity(request);
        submission.setStudentId(student.getId());
        if((request.getFile() == null || request.getFile().isEmpty()) &&
                (request.getTextResponse() == null) || request.getTextResponse().isEmpty()){
            log.error("file or text response must not be empty");
            throw new ApplicationException(ResponseCode.PARAMETER_IS_NOT_ENOUGH ,"file or text response must not be empty");
        }
        return submissionPort.submitAssignment(submission, request.getFile());
    }

    public List<AssignmentSubmissionDto> getAssignmentResponse(GetAssignSubmissionsRequest request) {
        var assignment = assignmentPort.getAssignmentById(request.getAssignmentId());
        var lecturer = authService.getLecturerByToken(request.getToken());
        var classDto = classPort.findClassById(assignment.getClassId());
        validateClassAccessService.checkLecturerAccessClassInfo(classDto, lecturer.getId());
        if(request.getGrade() != null){
            return submissionPort.responseSubmission(request);
        }
        else {
            return submissionPort.getAllSubmissions(assignment.getId());
        }
    }
}
