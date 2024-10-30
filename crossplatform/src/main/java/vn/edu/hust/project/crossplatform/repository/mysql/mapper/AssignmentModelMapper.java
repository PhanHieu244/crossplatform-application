package vn.edu.hust.project.crossplatform.repository.mysql.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vn.edu.hust.project.crossplatform.dto.AssignmentDto;
import vn.edu.hust.project.crossplatform.repository.mysql.model.AssignmentModel;

@Mapper
public interface AssignmentModelMapper {
    AssignmentModelMapper INSTANCE = Mappers.getMapper(AssignmentModelMapper.class);

    AssignmentModel toModel(AssignmentDto assignmentDto);
    AssignmentDto toEntity(AssignmentModel assignmentModel);
}
