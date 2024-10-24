package vn.edu.hust.project.crossplatform.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hust.project.crossplatform.dto.request.GetAttendanceListRequest;
import vn.edu.hust.project.crossplatform.dto.request.GetAttendanceRecordRequest;
import vn.edu.hust.project.crossplatform.dto.request.SetAttendanceStatusRequest;
import vn.edu.hust.project.crossplatform.dto.request.TakeAttendanceRequest;
import vn.edu.hust.project.crossplatform.dto.response.Resource;
import vn.edu.hust.project.crossplatform.service.IAttendanceService;

@RestController
@RequestMapping("/it5023e")
@RequiredArgsConstructor
public class AttendanceController
{
    private final IAttendanceService attendanceService;

    @PostMapping("/take_attendance")
    private ResponseEntity<Resource> takeAttendance(
            @Valid @RequestBody TakeAttendanceRequest request
    ){
        attendanceService.takeAttendance(request);
        return ResponseEntity.ok().body(new Resource(
                "take attendance successful"
        ));
    }

    @PostMapping("/set_attendance_status")
    private ResponseEntity<Resource> setAttendanceStatus(
            @Valid @RequestBody SetAttendanceStatusRequest request
    ){

        return ResponseEntity.ok().body(new Resource(
                attendanceService.setAttendanceStatus(request)
        ));
    }

    @GetMapping("/get_attendance_record")
    private ResponseEntity<Resource> getAttendanceRecord(
            @Valid @RequestBody GetAttendanceRecordRequest request
    ){

        return ResponseEntity.ok().body(new Resource(
                attendanceService.getAttendancesRecord(request)
        ));
    }

    @GetMapping("/get_attendance_list")
    private ResponseEntity<Resource> getAttendanceRecord(
            @Valid @RequestBody GetAttendanceListRequest request
    ){
        return ResponseEntity.ok().body(new Resource(
                attendanceService.getAttendanceList(request)
        ));
    }
}
