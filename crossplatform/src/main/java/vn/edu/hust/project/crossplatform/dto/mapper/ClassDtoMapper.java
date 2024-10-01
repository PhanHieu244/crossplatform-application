package vn.edu.hust.project.crossplatform.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vn.edu.hust.project.crossplatform.dto.ClassDto;
import vn.edu.hust.project.crossplatform.dto.request.CreateClassRequest;
import vn.edu.hust.project.crossplatform.dto.response.ClassInfoResponse;

import java.time.LocalDate;

@Mapper
public abstract class ClassDtoMapper {
    public final static ClassDtoMapper INSTANCE = Mappers.getMapper(ClassDtoMapper.class);

    private final static LocalDate startDate = LocalDate.of(2024, 9, 5);

    public abstract ClassDto toClassDto(CreateClassRequest request);
    public abstract ClassInfoResponse toClassInfoResponse(ClassDto classDto);

}
