package vn.edu.hust.project.crossplatform.port;

import vn.edu.hust.project.crossplatform.dto.ClassDto;
import vn.edu.hust.project.crossplatform.dto.request.CreateClassRequest;
import vn.edu.hust.project.crossplatform.dto.request.EditClassRequest;
import vn.edu.hust.project.crossplatform.repository.mysql.model.ClassModel;

import java.util.List;

public interface IClassPort {
    ClassDto createClass(ClassDto request);
    ClassDto findClassById(Integer classId);
    ClassDto findClassByCode(String classCode);
    ClassDto editClass(EditClassRequest request);
    List<ClassDto> getStudentClasses(Integer studentId);
    List<ClassDto> getLecturerClasses(Integer lecturerId);
    void deleteClass(String code);
}
