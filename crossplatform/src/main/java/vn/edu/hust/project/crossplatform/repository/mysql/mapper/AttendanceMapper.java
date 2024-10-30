package vn.edu.hust.project.crossplatform.repository.mysql.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vn.edu.hust.project.crossplatform.dto.AttendanceDto;
import vn.edu.hust.project.crossplatform.repository.mysql.model.AttendanceModel;

@Mapper
public interface AttendanceMapper {
    AttendanceMapper INSTANCE = Mappers.getMapper(AttendanceMapper.class);

    AttendanceModel entityToModel(AttendanceDto attendanceDto);
    AttendanceDto modelToEntity(AttendanceModel attendanceModel);
}
