package vn.edu.hust.project.crossplatform.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hust.project.crossplatform.dto.ClassDto;
import vn.edu.hust.project.crossplatform.dto.request.BaseClassRequest;
import vn.edu.hust.project.crossplatform.dto.request.BaseRequest;
import vn.edu.hust.project.crossplatform.dto.request.CreateClassRequest;
import vn.edu.hust.project.crossplatform.dto.request.EditClassRequest;
import vn.edu.hust.project.crossplatform.dto.response.Resource;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;
import vn.edu.hust.project.crossplatform.service.IAuthService;
import vn.edu.hust.project.crossplatform.service.IClassService;

@RestController
@AllArgsConstructor
@RequestMapping("/it5023e")
public class ClassController {

    private final IClassService classService;
    private final IAuthService authService;

    @PostMapping("/create_class")
    public ResponseEntity<Resource> createClass(
            @Valid @RequestBody CreateClassRequest request
    ){
        var lecturerId = authService.getLecturerByToken(request.getToken()).getId();
        return ResponseEntity.ok().body(
                new Resource(classService.createClass(request, lecturerId))
        );
    }

    @PostMapping("/edit_class")
    public ResponseEntity<Resource> editClass(
            @RequestBody EditClassRequest request
    ){
        var lecturerId = authService.getLecturerByToken(request.getToken()).getId();
        return ResponseEntity.ok().body(
                new Resource(classService.editClass(request))
        );
    }

    @DeleteMapping("/delete_class")
    public ResponseEntity<Resource> deleteClass(
            @RequestBody BaseClassRequest request
            ){
        authService.getAccountAndCheckRole(request.getToken(), Account.Role.LECTURER);
        classService.deleteClass(request.getClassId());
        return ResponseEntity.ok().body(
                new Resource(null)
        );
    }

    @GetMapping("/get_class_info")
    public ResponseEntity<Resource> getClassInfo(
            @Valid @RequestBody BaseClassRequest request
    ){
        var account = authService.getAccountByToken(request.getToken());
        return ResponseEntity.ok().body(
                new Resource(classService.getClassById(request.getClassId(), account))
        );
    }

    @GetMapping("/get_class_list")
    public ResponseEntity<Resource> getClassList(
            @Valid @RequestBody BaseRequest request
    ){
        var account = authService.getAccountByToken(request.getToken());
        return ResponseEntity.ok().body(
                new Resource(classService.getClassList(account))
        );
    }
}
