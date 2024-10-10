package vn.edu.hust.project.crossplatform.repository.mysql.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vn.edu.hust.project.crossplatform.dto.AssignmentSubmissionDto;
import vn.edu.hust.project.crossplatform.repository.mysql.model.AssignmentSubmissionModel;

@Mapper
public interface AssignSubmissionModelMapper {
    AssignSubmissionModelMapper INSTANCE = Mappers.getMapper(AssignSubmissionModelMapper.class);

    AssignmentSubmissionModel toModel(AssignmentSubmissionDto submissionDto);
    AssignmentSubmissionDto toEntity(AssignmentSubmissionModel submissionModel);
}
