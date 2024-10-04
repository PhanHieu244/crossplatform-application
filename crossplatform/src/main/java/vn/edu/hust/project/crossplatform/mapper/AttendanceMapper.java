package vn.edu.hust.project.crossplatform.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vn.edu.hust.project.crossplatform.dto.AttendanceDto;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Attendance;

@Mapper
public interface AttendanceMapper {
    AttendanceMapper INSTANCE = Mappers.getMapper(AttendanceMapper.class);

    Attendance entityToModel(AttendanceDto attendance);
    AttendanceDto modelToEntity(Attendance attendance);
}
