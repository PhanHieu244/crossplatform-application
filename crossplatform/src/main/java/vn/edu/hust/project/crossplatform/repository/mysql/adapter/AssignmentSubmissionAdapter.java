package vn.edu.hust.project.crossplatform.repository.mysql.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.hust.project.crossplatform.constant.ResponseCode;
import vn.edu.hust.project.crossplatform.constant.UploadDirectory;
import vn.edu.hust.project.crossplatform.dto.AssignmentSubmissionDto;
import vn.edu.hust.project.crossplatform.dto.request.GetAssignSubmissionsRequest;
import vn.edu.hust.project.crossplatform.dto.request.GradeSubmission;
import vn.edu.hust.project.crossplatform.dto.response.SubmitAssignmentResponse;
import vn.edu.hust.project.crossplatform.exception.base.ApplicationException;
import vn.edu.hust.project.crossplatform.helper.FileHelper;
import vn.edu.hust.project.crossplatform.port.IAssignmentSubmissionPort;
import vn.edu.hust.project.crossplatform.repository.mysql.IAssignmentSubmissionRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.mapper.AssignSubmissionModelMapper;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssignmentSubmissionAdapter implements IAssignmentSubmissionPort {
    private final IAssignmentSubmissionRepository submissionRepository;

    public List<AssignmentSubmissionDto> getAllSubmissions(Integer assignmentId) {
        return submissionRepository.findAllByAssignmentId(assignmentId).stream()
                .map(model -> {
                    var entity = AssignSubmissionModelMapper.INSTANCE.toEntity(model);
                    if(model.getFileUrl() != null && !model.getFileUrl().isEmpty()) {
                        entity.setFile(FileHelper.getFile(UploadDirectory.SUBMISSION ,model.getFileUrl()));
                    }
                    return entity;
                })
                .toList();
    }

    public AssignmentSubmissionDto getSubmission(Integer assignmentId, Integer studentId) {
        var model = submissionRepository.findByAssignmentIdAndStudentId(assignmentId, studentId)
                .orElseThrow(() -> {
                            log.error("submission not found for assignmentId: {}, studentId: {}",
                                    assignmentId, studentId);
                            return new ApplicationException(
                                    ResponseCode.NO_DATA_OR_END_OF_LIST_DATA,
                                    "submission not found for assignmentId: " + assignmentId
                                            + ", studentId: " + studentId
                            );
                        }
                );
        var entity = AssignSubmissionModelMapper.INSTANCE.toEntity(model);

        if(model.getFileUrl() != null && !model.getFileUrl().isEmpty()){
            entity.setFile(FileHelper.getFile(UploadDirectory.SUBMISSION ,model.getFileUrl()));
        }
        return entity;
    }

    @Override
    public Boolean existSubmission(Integer assignmentId, Integer studentId) {
        return submissionRepository.existsByAssignmentIdAndStudentId(assignmentId, studentId);
    }

    public SubmitAssignmentResponse submitAssignment(AssignmentSubmissionDto entity, MultipartFile file) {
        var submission = AssignSubmissionModelMapper.INSTANCE.toModel(entity);
        if(file != null && !file.isEmpty()) {
            var fileUrl = FileHelper.storeFile(UploadDirectory.SUBMISSION, file);
            submission.setFileUrl(fileUrl);
        }
        var submissionResponse = submissionRepository.save(submission);
        return SubmitAssignmentResponse.builder()
                .assignmentId(submissionResponse.getId())
                .build();
    }

    public List<AssignmentSubmissionDto> responseSubmission(GetAssignSubmissionsRequest request) {
        var gradeSubmission = request.getGrade();
        responseSubmission(gradeSubmission);
        return getAllSubmissions(request.getAssignmentId());
    }

    public void responseSubmission(GradeSubmission gradeSubmission) {
        var model = submissionRepository.findById(gradeSubmission.getSubmissionId())
                .orElseThrow(() -> {
                    log.error("submission not found");
                    return new ApplicationException(ResponseCode.NO_DATA_OR_END_OF_LIST_DATA ,"submission not found");
                });
        model.setGrade(gradeSubmission.getScore());
        submissionRepository.saveAndFlush(model);
    }
}
