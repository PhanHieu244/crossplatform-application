package vn.edu.hust.project.crossplatform.service;


import vn.edu.hust.project.crossplatform.dto.ClassDto;
import vn.edu.hust.project.crossplatform.dto.request.CreateClassRequest;
import vn.edu.hust.project.crossplatform.dto.request.EditClassRequest;
import vn.edu.hust.project.crossplatform.dto.response.ClassInfoResponse;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;

import java.util.List;

public interface IClassService {
    ClassDto createClass(CreateClassRequest request);
    ClassDto editClass(EditClassRequest request);
    void deleteClass(String code);
    void deleteClass(String code, Account account);
    ClassDto getClassByCode(String code);
    ClassDto getClassByCode(String code, Account account);
    List<ClassInfoResponse> getClassList(Account account);
}
