package vn.edu.hust.project.crossplatform.repository.mysql.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.hust.project.crossplatform.constant.ResponseCode;
import vn.edu.hust.project.crossplatform.constant.UploadDirectory;
import vn.edu.hust.project.crossplatform.dto.AssignmentDto;
import vn.edu.hust.project.crossplatform.exception.base.ApplicationException;
import vn.edu.hust.project.crossplatform.helper.FileHelper;
import vn.edu.hust.project.crossplatform.port.IAssignmentPort;
import vn.edu.hust.project.crossplatform.repository.mysql.IAssignmentRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.IAssignmentSubmissionRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.mapper.AssignmentModelMapper;
import vn.edu.hust.project.crossplatform.repository.mysql.model.AssignmentModel;

import java.util.List;
import java.util.function.Predicate;

@RequiredArgsConstructor
@Service
@Slf4j
public class AssignmentAdapter implements IAssignmentPort {
    private final IAssignmentRepository assignmentRepository;
    private final IAssignmentSubmissionRepository submissionRepository;

    public AssignmentDto createAssignment(AssignmentDto assignment, MultipartFile file) {
        var model = AssignmentModelMapper.INSTANCE.toModel(assignment);
        if(file != null) {
            model.setFileUrl(FileHelper.storeFile(UploadDirectory.ASSIGNMENT, file));
        }
        return AssignmentModelMapper.INSTANCE.toEntity(
                assignmentRepository.save(model)
        );
    }

    public AssignmentDto editAssignment(
            AssignmentDto assignment,
            MultipartFile file,
            Predicate<AssignmentModel> editPredicate) {
        var existedAssignment = getAssignmentById(assignment.getId());
        if(editPredicate != null) {
            if(!editPredicate.test(existedAssignment)) {
                log.error("not allowed to edit assignment");
                throw new ApplicationException(ResponseCode.NOT_ACCESS, "not allowed to edit this assignment");
            }
        }
        String fileUrl = null;
        if(file != null && !file.isEmpty()) {
            fileUrl = FileHelper.storeFile(UploadDirectory.ASSIGNMENT, file);
            existedAssignment.setFileUrl(fileUrl);
        }
        if(assignment.getDeadline() != null){
            existedAssignment.setDeadline(assignment.getDeadline());
        }
        if(assignment.getDeadline() != null){
            existedAssignment.setDeadline(assignment.getDeadline());
        }
        var entity = AssignmentModelMapper.INSTANCE.toEntity(
                assignmentRepository.save(existedAssignment)
        );
        if(fileUrl != null) entity.setFile(FileHelper.getFile(UploadDirectory.ASSIGNMENT, fileUrl));
        return entity;
    }

    public void deleteAssignment(Integer assignmentId) {
        if(submissionRepository.existsById(assignmentId)) {
            throw new ApplicationException(ResponseCode.PARAMETER_VALUE_IS_INVALID,
                    "cant delete this assignment when it is already submitted");
        }
        assignmentRepository.deleteById(assignmentId);
    }

    public List<AssignmentDto> getAllAssignments(Integer classId) {
        return assignmentRepository.findAllByClassId(classId).stream()
                .map(model -> {
                    var entity = AssignmentModelMapper.INSTANCE.toEntity(model);
                    if(model.getFileUrl() != null && !model.getFileUrl().isEmpty()) {
                        entity.setFile(FileHelper.getFile(UploadDirectory.ASSIGNMENT ,model.getFileUrl()));
                    }
                    return entity;
                })
                .toList();
    }

    public AssignmentModel getAssignmentById(Integer assignmentId) {
        return assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> {
                    log.error("getAssignment err: {}", assignmentId);
                    return new ApplicationException("assignment not found");
                });
    }
}
