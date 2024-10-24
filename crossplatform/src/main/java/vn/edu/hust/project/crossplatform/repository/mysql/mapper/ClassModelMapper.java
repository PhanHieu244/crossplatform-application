package vn.edu.hust.project.crossplatform.repository.mysql.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import vn.edu.hust.project.crossplatform.dto.ClassDto;
import vn.edu.hust.project.crossplatform.repository.mysql.model.ClassModel;

@Mapper
public interface ClassModelMapper {
    ClassModelMapper INSTANCE = Mappers.getMapper(ClassModelMapper.class);

    @Mapping(target = "code", source = "classId")
    ClassModel toModel(ClassDto entity);
    @Mapping(target = "classId", source = "code")
    ClassDto toEntity(ClassModel entity);
}
