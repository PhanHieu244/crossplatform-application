package vn.edu.hust.project.crossplatform.repository.mysql.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vn.edu.hust.project.crossplatform.dto.StudentDto;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Student;

@Mapper
public interface StudentModelMapper {
    StudentModelMapper INSTANCE = Mappers.getMapper(StudentModelMapper.class);

    Student toModel(StudentDto studentDto);
    StudentDto toEntity(Student student);
}
