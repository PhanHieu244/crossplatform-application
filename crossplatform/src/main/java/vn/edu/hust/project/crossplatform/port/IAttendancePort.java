package vn.edu.hust.project.crossplatform.port;

import vn.edu.hust.project.crossplatform.constant.AttendanceStatus;
import vn.edu.hust.project.crossplatform.dto.AttendanceDto;
import vn.edu.hust.project.crossplatform.dto.ClassDto;
import vn.edu.hust.project.crossplatform.dto.request.GetAttendanceListRequest;
import vn.edu.hust.project.crossplatform.dto.request.TakeAttendanceRequest;
import vn.edu.hust.project.crossplatform.dto.response.AttendanceListResponse;
import vn.edu.hust.project.crossplatform.dto.response.StudentAttendancesResponse;

public interface IAttendancePort {

    void takeAttendance(TakeAttendanceRequest request);

    void takeAttendance(TakeAttendanceRequest request, ClassDto classDto);

    AttendanceListResponse getAttendanceList(GetAttendanceListRequest request, Integer classId);

    StudentAttendancesResponse getAttendanceRecord(Integer studentId, ClassDto classDto);

    AttendanceDto setAttendanceStatus(Integer attendanceId, AttendanceStatus status);

    AttendanceDto getAttendance(Integer attendanceId);

}
