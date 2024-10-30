package vn.edu.hust.project.crossplatform.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import vn.edu.hust.project.crossplatform.dto.AssignmentDto;
import vn.edu.hust.project.crossplatform.dto.request.CreateAssignmentRequest;
import vn.edu.hust.project.crossplatform.dto.request.EditAssignmentRequest;

@Mapper
public interface AssignmentMapper {
    AssignmentMapper INSTANCE = Mappers.getMapper(AssignmentMapper.class);

    @Mapping(source = "file", target = "file", ignore = true)
    @Mapping(source = "classId", target = "classId", ignore = true)
    AssignmentDto requestToEntity(CreateAssignmentRequest request);
    @Mapping(source = "file", target = "file", ignore = true)
    @Mapping(source = "assignmentId", target = "id")
    AssignmentDto requestToEntity(EditAssignmentRequest request);
}
