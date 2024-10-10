package vn.edu.hust.project.crossplatform.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.dto.AttendanceDto;
import vn.edu.hust.project.crossplatform.dto.request.GetAttendanceListRequest;
import vn.edu.hust.project.crossplatform.dto.request.GetAttendanceRecordRequest;
import vn.edu.hust.project.crossplatform.dto.request.SetAttendanceStatusRequest;
import vn.edu.hust.project.crossplatform.dto.request.TakeAttendanceRequest;
import vn.edu.hust.project.crossplatform.dto.response.AttendanceListResponse;
import vn.edu.hust.project.crossplatform.dto.response.StudentAttendancesResponse;
import vn.edu.hust.project.crossplatform.port.IAttendancePort;
import vn.edu.hust.project.crossplatform.service.IAttendanceService;
import vn.edu.hust.project.crossplatform.service.IAuthService;
import vn.edu.hust.project.crossplatform.service.IValidateClassAccessService;

@Service
@Slf4j
@RequiredArgsConstructor
public class AttendanceService implements IAttendanceService {
    private final IAttendancePort attendancePort;
    private final IAuthService authService;
    private final IValidateClassAccessService validateClassAccessService;

    public void takeAttendance(TakeAttendanceRequest request){
        var account = authService.getAccountByToken(request.getToken());
        var classDto = validateClassAccessService.getAndCheckEditClass(account, request.getClassId());
        attendancePort.takeAttendance(request, classDto);
    }

    @Override
    public AttendanceListResponse getAttendanceList(GetAttendanceListRequest request) {
        var account = authService.getAccountByToken(request.getToken());
        var classDto = validateClassAccessService.getAndCheckEditClass(account, request.getClassId());
        return attendancePort.getAttendanceList(request, classDto.getId());
    }

    @Override
    public StudentAttendancesResponse getAttendancesRecord(GetAttendanceRecordRequest request) {
        var student = authService.getStudentByToken(request.getToken());
        var classDto = validateClassAccessService.checkStudentBelongToClass(student.getId(), request.getClassId());
        return attendancePort.getAttendanceRecord(student.getId(), classDto);
    }

    @Override
    public AttendanceDto setAttendanceStatus(SetAttendanceStatusRequest request) {
        return attendancePort.setAttendanceStatus(request.getAttendanceId(), request.getStatus());
    }
}
