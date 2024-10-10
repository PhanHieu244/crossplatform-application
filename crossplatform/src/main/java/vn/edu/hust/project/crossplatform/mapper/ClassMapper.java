package vn.edu.hust.project.crossplatform.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vn.edu.hust.project.crossplatform.dto.ClassDto;
import vn.edu.hust.project.crossplatform.dto.request.CreateClassRequest;
import vn.edu.hust.project.crossplatform.dto.response.ClassInfoResponse;

@Mapper
public interface ClassMapper {
     ClassMapper INSTANCE = Mappers.getMapper(ClassMapper.class);

    ClassDto toClassDto(CreateClassRequest request);
    ClassInfoResponse toClassInfoResponse(ClassDto classDto);

}
