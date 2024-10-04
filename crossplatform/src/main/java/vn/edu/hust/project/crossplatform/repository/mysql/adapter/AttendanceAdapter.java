package vn.edu.hust.project.crossplatform.repository.mysql.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.constant.AttendanceStatus;
import vn.edu.hust.project.crossplatform.constant.ResponseCode;
import vn.edu.hust.project.crossplatform.dto.AttendanceDto;
import vn.edu.hust.project.crossplatform.dto.ClassDto;
import vn.edu.hust.project.crossplatform.dto.request.GetAttendanceListRequest;
import vn.edu.hust.project.crossplatform.dto.request.TakeAttendanceRequest;
import vn.edu.hust.project.crossplatform.dto.response.AttendanceListResponse;
import vn.edu.hust.project.crossplatform.dto.response.AttendanceStudentDetail;
import vn.edu.hust.project.crossplatform.dto.response.StudentAttendancesResponse;
import vn.edu.hust.project.crossplatform.exception.base.ApplicationException;
import vn.edu.hust.project.crossplatform.mapper.AttendanceMapper;
import vn.edu.hust.project.crossplatform.port.IAttendancePort;
import vn.edu.hust.project.crossplatform.repository.mysql.IAttendanceRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.IClassDetailRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.IClassRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.mapper.ClassModelMapper;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Attendance;
import vn.edu.hust.project.crossplatform.repository.mysql.model.ClassDetailModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AttendanceAdapter implements IAttendancePort {
    private final IAttendanceRepository attendanceRepository;
    private final IClassDetailRepository classDetailRepository;
    private final IClassRepository classRepository;

    public void takeAttendance(TakeAttendanceRequest request) {
        var classModel = classRepository.findClassByCode(request.getClassId())
                .orElseThrow(() -> {
                    log.error("class not found");
                    return new ApplicationException(ResponseCode.NO_DATA_OR_END_OF_LIST_DATA,"Class not found");
                });
        takeAttendance(request, ClassModelMapper.INSTANCE.toEntity(classModel));
    }

    public void takeAttendance(TakeAttendanceRequest request, ClassDto classDto) {
        if(!IsAttendanceDateValid(request.getDate(), classDto)){
            log.error("attendance date is invalid");
            throw new ApplicationException(ResponseCode.PARAMETER_VALUE_IS_INVALID, "attendance date is invalid");
        }

        var classId = classDto.getId();
        var classDetailList = classDetailRepository.findByClassId(classId);
        var absentHashmap = request.getAttendanceList()
                .stream()
                .collect(Collectors.toMap(
                        studentId -> studentId,
                        studentId -> true,
                        (existing, replacement) -> existing,
                        HashMap::new
                ));
        List<Attendance> attendances = new ArrayList<>();
        for(var classDetail : classDetailList) {
            var attendance = new Attendance();
            var status = absentHashmap.containsKey(classDetail.getStudentId()) ?
                    AttendanceStatus.UNEXCUSED_ABSENCE : AttendanceStatus.PRESENT;
            attendance.setClassDetailId(classDetail.getId());
            attendance.setAttendanceStatus(status);
            attendance.setAttendanceTime(request.getDate());
            attendances.add(attendance);
        }
        attendanceRepository.saveAll(attendances);
    }

    public AttendanceListResponse getAttendanceList(GetAttendanceListRequest request, Integer classId) {
        try {
            var classDetailHashMap = classDetailRepository.findByClassId(classId)
                    .stream()
                    .collect(Collectors.toMap(
                            ClassDetailModel::getId,
                            classDetailModel -> classDetailModel,
                            (existing, replacement) -> existing,
                            HashMap::new
                    ));
            var classDetailIds = classDetailHashMap.values().stream()
                    .map(ClassDetailModel::getId)
                    .toList();
            var attendances = attendanceRepository.getAllAtAttendanceTimeAndClassDetailIds(request.getDate(), classDetailIds);
            var attendanceDetails = attendances.stream()
                    .map(attendance ->
                        AttendanceStudentDetail.builder()
                                .attendanceId(attendance.getId())
                                .studentId(classDetailHashMap.get(attendance.getClassDetailId()).getStudentId())
                                .status(attendance.getAttendanceStatus())
                                .build()
                    ).toList();
            return AttendanceListResponse.builder()
                    .attendanceStudentDetails(attendanceDetails)
                    .build();
        }
        catch(ApplicationException e) {
            log.error("get attendance list error", e);
            throw new ApplicationException("get attendance list error");
        }
    }

    public StudentAttendancesResponse getAttendanceRecord(Integer studentId, ClassDto classDto) {
        var classDetail = classDetailRepository.findByStudentIdAndClassId(studentId, classDto.getId())
                .orElseThrow(() -> {
                    log.error("student not belong this class id " + "classDto.getId()");
                    return new ApplicationException(
                            ResponseCode.NO_DATA_OR_END_OF_LIST_DATA,
                            "student not belong this class id " + classDto.getId());
                });
        var attendanceList = attendanceRepository.getAttendancesByClassDetailId(classDetail.getId());
        var absentsDates = attendanceList.stream()
                .filter(attendance -> attendance.getAttendanceStatus() != AttendanceStatus.PRESENT)
                .map(Attendance::getAttendanceTime)
                .toList();
        return StudentAttendancesResponse.builder()
                .absentDates(absentsDates)
                .build();
    }

    public AttendanceDto setAttendanceStatus(Integer attendanceId ,AttendanceStatus status) {
        var attendanceModel = getAttendanceModel(attendanceId);
        attendanceModel.setAttendanceStatus(status);
        return AttendanceMapper.INSTANCE.modelToEntity(attendanceRepository.save(attendanceModel));

    }

    public AttendanceDto getAttendance(Integer attendanceId) {
        return AttendanceMapper.INSTANCE.modelToEntity(getAttendanceModel(attendanceId));
    }

    public Attendance getAttendanceModel(Integer attendanceId) {
        return attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> {
                    log.error("attendance not found");
                    return new ApplicationException(ResponseCode.NO_DATA_OR_END_OF_LIST_DATA, "attendance not found");
                });
    }

    private static boolean IsAttendanceDateValid(LocalDate date, ClassDto classModel) {
        return date.isAfter(classModel.getStartDate()) && date.isBefore(classModel.getEndDate());
    }
}
