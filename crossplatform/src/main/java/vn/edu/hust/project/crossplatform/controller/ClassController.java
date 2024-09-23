package vn.edu.hust.project.crossplatform.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hust.project.crossplatform.dto.request.ClassRequest;


import vn.edu.hust.project.crossplatform.dto.request.GetClassRequest;
import vn.edu.hust.project.crossplatform.dto.response.ClassResponse;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;
import vn.edu.hust.project.crossplatform.service.ClassService;
import vn.edu.hust.project.crossplatform.service.TokenService;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/it4788")
public class ClassController {

    @Autowired
    private ClassService classService;
    @Autowired
    private TokenService tokenService;
    @PostMapping("/create_class")
    public ResponseEntity<?> createClass(@RequestBody @Valid ClassRequest request) {
        if (request.getStartDate() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ClassResponse(9994, "Start_date not null"));
        }
        if (request.getEndDate() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ClassResponse(9994, "End_date not null"));
        }
        if (request.getSemester().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ClassResponse(9994, "Semester not null"));
        }
        if (request.getClassName().length() > 100 ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ClassResponse(1004, "Classname error"));
        }
        if (request.getMaxStudents() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ClassResponse(9994, "MaxStudents not null"));
        } else if(request.getMaxStudents() > 500){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ClassResponse(1004, "MaxStudents less than equal to 50"));
        }
        if (request.getMaxStudents() != request.getMaxStudents().intValue()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ClassResponse(1004, "MaxStudents must be Integer"));
        }
        // Kiểm tra logic ngày bắt đầu và kết thúc
        if (request.getStartDate().isAfter(request.getEndDate())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ClassResponse(1004, "Start_date less than equal to End_date"));
        }
        try {
            Account account = tokenService.validateToken(request.getToken());
            if (account == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ClassResponse(9998, "Token is invalid"));
            }

            if (!"LECTURER".equals(account.getRole())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(new ClassResponse(1003, "Access Denied"));
            }
            if (classService.existsByClass_name(request.getClassName())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new ClassResponse(9996, "ClassName existed"));
            }
            int class_id = classService.createClass(request);
            return ResponseEntity.ok(new ClassResponse("OK",1000,class_id));

        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("1001 | Server Error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PostMapping("/get_class_list")
    public ResponseEntity<?> getClassList(@RequestBody @Valid GetClassRequest request) {
        try {
            Account account = tokenService.validateToken(request.getToken());
            if (account == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ClassResponse(9998, "Token is invalid"));
            }

            if (!"LECTURER".equals(account.getRole())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(new ClassResponse(1003, "Access Denied"));
            }
            List<Map<String, Object>> classList = classService.getClassList(request);
            Map<String, Object> response = new HashMap<>();
            response.put("code", "1000");
            response.put("message", "OK");
            response.put("data", classList);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", e.getMessage()));
        }
    }
}


