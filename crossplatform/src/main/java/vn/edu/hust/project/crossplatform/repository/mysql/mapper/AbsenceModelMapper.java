package vn.edu.hust.project.crossplatform.repository.mysql.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vn.edu.hust.project.crossplatform.dto.AbsenceRequestDto;
import vn.edu.hust.project.crossplatform.repository.mysql.model.AbsenceRequestModel;

@Mapper
public interface AbsenceModelMapper {
    AbsenceModelMapper INSTANCE = Mappers.getMapper(AbsenceModelMapper.class);

    AbsenceRequestModel toModel(AbsenceRequestDto absenceDto);
    AbsenceRequestDto toEntity(AbsenceRequestModel absenceRequest);
}
