package vn.edu.hust.project.crossplatform.service;

import vn.edu.hust.project.crossplatform.dto.AbsenceRequestDto;
import vn.edu.hust.project.crossplatform.dto.request.CreateAbsenceRequest;
import vn.edu.hust.project.crossplatform.dto.request.GetAbsenceRequests;
import vn.edu.hust.project.crossplatform.dto.request.ReviewAbsenceRequest;
import vn.edu.hust.project.crossplatform.dto.response.AbsenceRequestResponse;

import java.util.List;

public interface IAbsenceRequestService {
    AbsenceRequestResponse createAbsenceRequest(CreateAbsenceRequest request);
    AbsenceRequestDto toAbsenceRequestDto(CreateAbsenceRequest request);
    void reviewAbsenceRequest(ReviewAbsenceRequest request);
    List<AbsenceRequestDto> getAbsenceRequests(GetAbsenceRequests request);
}
