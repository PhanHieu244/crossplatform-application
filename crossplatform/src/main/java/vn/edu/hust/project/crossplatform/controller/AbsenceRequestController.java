package vn.edu.hust.project.crossplatform.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hust.project.crossplatform.dto.request.GetAbsenceRequests;
import vn.edu.hust.project.crossplatform.dto.request.CreateAbsenceRequest;
import vn.edu.hust.project.crossplatform.dto.request.ReviewAbsenceRequest;
import vn.edu.hust.project.crossplatform.dto.response.Resource;
import vn.edu.hust.project.crossplatform.service.impl.AbsenceRequestService;

@RestController
@RequestMapping("/it5023e")
@RequiredArgsConstructor
public class AbsenceRequestController {
    private final AbsenceRequestService absenceRequestService;

    @PostMapping("/request_absence")
    private ResponseEntity<Resource> createAbsenceRequest(
            @Valid @RequestBody CreateAbsenceRequest request
    ){
        return ResponseEntity.ok().body(new Resource(
            absenceRequestService.createAbsenceRequest(request)
        ));
    }

    @PostMapping("/review_absence_request")
    private ResponseEntity<Resource> reviewAbsenceRequest(
            @Valid @RequestBody ReviewAbsenceRequest request
    ){
        absenceRequestService.reviewAbsenceRequest(request);
        return ResponseEntity.ok().body(new Resource(
                "Review successful"
        ));
    }

    @PostMapping("/get_absence_requests")
    private ResponseEntity<Resource> reviewAbsenceRequest(
            @Valid @RequestBody GetAbsenceRequests request
    ){

        return ResponseEntity.ok().body(new Resource(
                absenceRequestService.getAbsenceRequests(request)
        ));
    }
}
