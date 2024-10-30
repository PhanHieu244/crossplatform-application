package vn.edu.hust.project.crossplatform.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hust.project.crossplatform.dto.request.AddStudentRequest;
import vn.edu.hust.project.crossplatform.dto.response.Resource;
import vn.edu.hust.project.crossplatform.service.IClassDetailService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/it5023e")
public class ClassManagerController {
    private final IClassDetailService classDetailService;

    @PostMapping("/add_student")
    public ResponseEntity<Resource> addStudent(
            @Valid @RequestBody AddStudentRequest request
    ) {
        classDetailService.addStudent(request);
        return ResponseEntity.ok().body(
                new Resource(
                        "add student with account id " + request.getAccountId() +
                                " to classId " + request.getClassId() + " success")
        );
    }
}
