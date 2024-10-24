package vn.edu.hust.project.crossplatform.service;

import vn.edu.hust.project.crossplatform.dto.AttendanceDto;
import vn.edu.hust.project.crossplatform.dto.request.GetAttendanceListRequest;
import vn.edu.hust.project.crossplatform.dto.request.GetAttendanceRecordRequest;
import vn.edu.hust.project.crossplatform.dto.request.SetAttendanceStatusRequest;
import vn.edu.hust.project.crossplatform.dto.request.TakeAttendanceRequest;
import vn.edu.hust.project.crossplatform.dto.response.AttendanceListResponse;
import vn.edu.hust.project.crossplatform.dto.response.StudentAttendancesResponse;

public interface IAttendanceService {
    void takeAttendance(TakeAttendanceRequest request);

    AttendanceListResponse getAttendanceList(GetAttendanceListRequest request);

    StudentAttendancesResponse getAttendancesRecord(GetAttendanceRecordRequest request);

    AttendanceDto setAttendanceStatus(SetAttendanceStatusRequest request);
}
