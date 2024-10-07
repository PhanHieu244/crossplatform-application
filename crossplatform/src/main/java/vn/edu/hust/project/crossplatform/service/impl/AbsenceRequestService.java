package vn.edu.hust.project.crossplatform.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.constant.AbsenceRequestStatus;
import vn.edu.hust.project.crossplatform.constant.ResponseCode;
import vn.edu.hust.project.crossplatform.dto.AbsenceRequestDto;
import vn.edu.hust.project.crossplatform.dto.request.CreateAbsenceRequest;
import vn.edu.hust.project.crossplatform.dto.request.GetAbsenceRequests;
import vn.edu.hust.project.crossplatform.dto.request.ReviewAbsenceRequest;
import vn.edu.hust.project.crossplatform.dto.response.AbsenceRequestResponse;
import vn.edu.hust.project.crossplatform.exception.base.ApplicationException;
import vn.edu.hust.project.crossplatform.port.IAbsenceRequestPort;
import vn.edu.hust.project.crossplatform.port.IClassDetailPort;
import vn.edu.hust.project.crossplatform.port.IClassPort;
import vn.edu.hust.project.crossplatform.service.IAbsenceRequestService;
import vn.edu.hust.project.crossplatform.service.IAuthService;
import vn.edu.hust.project.crossplatform.service.IValidateClassAccessService;
import vn.edu.hust.project.crossplatform.utils.ClassDtoUtils;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AbsenceRequestService implements IAbsenceRequestService {
    private final IAuthService authService;
    private final IAbsenceRequestPort absenceRequestPort;
    private final IClassDetailPort classDetailPort;
    private final IClassPort classPort;
    private final IValidateClassAccessService validateClassAccessService;

    public AbsenceRequestResponse createAbsenceRequest(CreateAbsenceRequest request) {
        var absenceDto = absenceRequestPort.createAbsenceRequest(toAbsenceRequestDto(request));
        return new AbsenceRequestResponse(absenceDto.getId());
    }

    public AbsenceRequestDto toAbsenceRequestDto(CreateAbsenceRequest request){
        var classEntity = classPort.findClassByCode(request.getClassId());
        if(!ClassDtoUtils.IsDateValid(request.getDate(), classEntity)){
            log.error("invalid date");
            throw new ApplicationException(ResponseCode.PARAMETER_VALUE_IS_INVALID, "invalid date");
        }
        var student = authService.getStudentByToken(request.getToken());
        var classDetailId = classDetailPort.getClassDetailId(classEntity.getId(), student.getId());
        return AbsenceRequestDto.builder()
                .classDetailId(classDetailId)
                .absenceDate(request.getDate())
                .reason(request.getReason())
                .status(AbsenceRequestStatus.PENDING)
                .build();
    }

    public void reviewAbsenceRequest(ReviewAbsenceRequest request){
        var absenceRequest = absenceRequestPort.getAbsenceRequest(request.getRequestId());
        var classDetail = classDetailPort.getClassDetail(absenceRequest.getClassDetailId());
        var classEntity = classPort.findClassById(classDetail.getClassId());
        var lecturer = authService.getLecturerByToken(request.getToken());
        if(!lecturer.getId().equals(classEntity.getLecturerId())){
            log.error("lecturer not match");
            throw new ApplicationException(ResponseCode.NOT_ACCESS, "lecturer not match");
        }
        absenceRequestPort.reviewAbsenceRequest(request);
    }

    public List<AbsenceRequestDto> getAbsenceRequests(GetAbsenceRequests request){
        var account = authService.getAccountByToken(request.getToken());
        var classEntity = validateClassAccessService.getAndCheckEditClass(account, request.getClassId());
        if(request.getDate() != null && !ClassDtoUtils.IsDateValid(request.getDate(), classEntity)){
            log.error("get absence request fail by invalid date");
            throw new ApplicationException(ResponseCode.PARAMETER_VALUE_IS_INVALID, "invalid date");
        }
        return absenceRequestPort.getAbsenceRequests(request, classEntity.getId());
    }
}
