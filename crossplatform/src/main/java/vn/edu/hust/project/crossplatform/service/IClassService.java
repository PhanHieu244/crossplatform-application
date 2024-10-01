package vn.edu.hust.project.crossplatform.service;


import vn.edu.hust.project.crossplatform.dto.ClassDto;
import vn.edu.hust.project.crossplatform.dto.request.CreateClassRequest;
import vn.edu.hust.project.crossplatform.dto.request.EditClassRequest;
import vn.edu.hust.project.crossplatform.dto.response.ClassInfoResponse;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;

import java.util.List;

public interface IClassService {
    ClassDto createClass(CreateClassRequest request, Integer lecturerId);
    ClassDto editClass(EditClassRequest request);
    void deleteClass(Integer classId);
    void deleteClass(Integer classId, Integer lectureId);
    ClassDto getClassById(Integer classId);
    ClassDto getClassById(Integer classId, Account account);
    List<ClassInfoResponse> getClassList(Account account);
}
