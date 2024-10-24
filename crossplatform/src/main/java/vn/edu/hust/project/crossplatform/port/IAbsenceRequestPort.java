package vn.edu.hust.project.crossplatform.port;

import vn.edu.hust.project.crossplatform.dto.AbsenceRequestDto;
import vn.edu.hust.project.crossplatform.dto.request.GetAbsenceRequests;
import vn.edu.hust.project.crossplatform.dto.request.ReviewAbsenceRequest;

import java.util.List;

public interface IAbsenceRequestPort {
    AbsenceRequestDto createAbsenceRequest(AbsenceRequestDto absenceDto);
    void reviewAbsenceRequest(ReviewAbsenceRequest request);
    List<AbsenceRequestDto> getAbsenceRequests(GetAbsenceRequests request, Integer classId);
    List<AbsenceRequestDto> getAbsenceRequests(List<Integer> requestIds);
    AbsenceRequestDto getAbsenceRequest(Integer requestId);
}
