package vn.edu.hust.project.crossplatform.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.constant.ResponseCode;
import vn.edu.hust.project.crossplatform.dto.AssignmentDto;
import vn.edu.hust.project.crossplatform.dto.request.CreateAssignmentRequest;
import vn.edu.hust.project.crossplatform.dto.request.DeleteAssignmentRequest;
import vn.edu.hust.project.crossplatform.dto.request.EditAssignmentRequest;
import vn.edu.hust.project.crossplatform.dto.request.GetAssignmentInClassRequest;
import vn.edu.hust.project.crossplatform.exception.UnauthorizedException;
import vn.edu.hust.project.crossplatform.exception.base.ApplicationException;
import vn.edu.hust.project.crossplatform.mapper.AssignmentMapper;
import vn.edu.hust.project.crossplatform.port.IAssignmentPort;
import vn.edu.hust.project.crossplatform.service.IAssignmentService;
import vn.edu.hust.project.crossplatform.service.IAuthService;
import vn.edu.hust.project.crossplatform.service.IValidateClassAccessService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssignmentService implements IAssignmentService {
    private final IAssignmentPort assignmentPort;
    private final IAuthService authService;
    private final IValidateClassAccessService validateClassAccessService;

    public Integer createAssignment(CreateAssignmentRequest request) {
        checkDeadlineIsValid(request.getDeadline());
        var classCode = request.getClassId();
        var lecturer = authService.getLecturerByToken(request.getToken());
        var classEntity = validateClassAccessService.getAndCheckEditClass(lecturer, classCode);
        var assignmentEntity = AssignmentMapper.INSTANCE.requestToEntity(request);
        assignmentEntity.setLecturerId(lecturer.getId());
        assignmentEntity.setClassId(classEntity.getId());
        return assignmentPort.createAssignment(assignmentEntity, request.getFile()).getId();
    }

    public AssignmentDto editAssignment(EditAssignmentRequest request) {
        checkDeadlineIsValid(request.getDeadline());
        var lecturer = authService.getLecturerByToken(request.getToken());
        var editAssignment = AssignmentMapper.INSTANCE.requestToEntity(request);
        return assignmentPort.editAssignment(
                editAssignment,
                request.getFile(),
                //check edit is valid by account
                assignmentModel -> assignmentModel.getLecturerId().equals(lecturer.getId())
        );
    }

    public void deleteAssignment(DeleteAssignmentRequest request) {
        var lecturer = authService.getLecturerByToken(request.getToken());
        var assignmentEntity = assignmentPort.getAssignmentById(request.getAssignmentId());
        if(!assignmentEntity.getLecturerId().equals(lecturer.getId())) {
            log.error("lecturer not allowed to delete this assignment {}", request.getAssignmentId());
            throw new UnauthorizedException("lecturer not allowed to delete this assignment" + request.getAssignmentId());
        };
        assignmentPort.deleteAssignment(request.getAssignmentId());
    }

    public List<AssignmentDto> getAssignmentInClass(GetAssignmentInClassRequest request) {
        var classEntity = validateClassAccessService.getAndCheckAccessClassInfo(request.getClassId(), request.getToken());
        return assignmentPort.getAllAssignments(classEntity.getId());
    }

    public static void checkDeadlineIsValid(LocalDateTime deadline){
        if(deadline.isBefore(LocalDateTime.now())){
            log.error("Deadline is invalid");
            throw new ApplicationException(
                    ResponseCode.PARAMETER_VALUE_IS_INVALID,
                    "deadline cannot be before current datetime"
                    );
        }
    }
}
