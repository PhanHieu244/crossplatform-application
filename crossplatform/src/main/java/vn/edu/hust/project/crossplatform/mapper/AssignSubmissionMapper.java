package vn.edu.hust.project.crossplatform.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import vn.edu.hust.project.crossplatform.dto.AssignmentSubmissionDto;
import vn.edu.hust.project.crossplatform.dto.request.SubmitAssignmentRequest;

@Mapper
public interface AssignSubmissionMapper {
    AssignSubmissionMapper INSTANCE = Mappers.getMapper(AssignSubmissionMapper.class);

    @Mapping(source = "file", target = "file", ignore = true)
    AssignmentSubmissionDto requestToEntity(SubmitAssignmentRequest request);
}
