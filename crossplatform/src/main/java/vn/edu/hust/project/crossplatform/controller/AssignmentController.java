package vn.edu.hust.project.crossplatform.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hust.project.crossplatform.dto.request.*;
import vn.edu.hust.project.crossplatform.dto.response.Resource;
import vn.edu.hust.project.crossplatform.service.impl.AssignSubmissionService;
import vn.edu.hust.project.crossplatform.service.impl.AssignmentService;

@RestController
@RequestMapping("/it5023e")
@RequiredArgsConstructor
public class AssignmentController {
    private final AssignmentService assignmentService;
    private final AssignSubmissionService submissionService;

    @PostMapping(value = "/create_survey", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    private ResponseEntity<Resource> createAssignment(
            @Valid @ModelAttribute CreateAssignmentRequest request
    ){
        return ResponseEntity.ok().body(new Resource(
                assignmentService.createAssignment(request)
        ));
    }

    @PostMapping(value = "/edit_survey", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    private ResponseEntity<Resource> editAssignment(
            @Valid @ModelAttribute EditAssignmentRequest request
    ){
        return ResponseEntity.ok().body(
                new Resource( assignmentService.editAssignment(request) )
        );
    }

    @PostMapping(value = "/get_all_surveys")
    private ResponseEntity<Resource> getAllAssignments(
            @Valid @RequestBody GetAssignmentInClassRequest request
    ){
        return ResponseEntity.ok()
                //.contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(
                        new Resource( assignmentService.getAssignmentInClass(request) )
                );
    }

    @PostMapping(value = "/delete_survey")
    private ResponseEntity<Resource> deleteAssignment(
            @Valid @RequestBody DeleteAssignmentRequest request
    ){
        assignmentService.deleteAssignment(request);
        return ResponseEntity.ok().body(
                new Resource("delete assignment successful"));
    }

    @PostMapping(value = "/submit_survey", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    private ResponseEntity<Resource> submitAssignment(
            @Valid @ModelAttribute SubmitAssignmentRequest request
    ){
        return ResponseEntity.ok().body(
                new Resource(submissionService.submitAssignment(request))
        );
    }

    @PostMapping(value = "/get_survey_response")
    private ResponseEntity<Resource> submitAssignment(
            @Valid @RequestBody GetAssignSubmissionsRequest request
            ){
        return ResponseEntity.ok().body(
                new Resource(submissionService.getAssignmentResponse(request))
        );
    }
}
