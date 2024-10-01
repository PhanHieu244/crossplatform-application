package vn.edu.hust.project.crossplatform.repository.mysql.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import vn.edu.hust.project.crossplatform.dto.ClassDto;
import vn.edu.hust.project.crossplatform.repository.mysql.model.ClassModel;

@Mapper
public interface ClassModelMapper {
    ClassModelMapper INSTANCE = Mappers.getMapper(ClassModelMapper.class);

    ClassModel toModel(ClassDto entity);
    ClassDto toEntity(ClassModel entity);
    void updateModelFromEntity(ClassDto classDto, @MappingTarget ClassModel entity);
}
