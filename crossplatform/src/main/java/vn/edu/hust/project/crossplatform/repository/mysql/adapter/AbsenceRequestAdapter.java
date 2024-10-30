package vn.edu.hust.project.crossplatform.repository.mysql.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.dto.AbsenceRequestDto;
import vn.edu.hust.project.crossplatform.dto.request.GetAbsenceRequests;
import vn.edu.hust.project.crossplatform.dto.request.ReviewAbsenceRequest;
import vn.edu.hust.project.crossplatform.exception.base.ApplicationException;
import vn.edu.hust.project.crossplatform.port.IAbsenceRequestPort;
import vn.edu.hust.project.crossplatform.repository.mysql.IAbsenceRequestRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.mapper.AbsenceModelMapper;
import vn.edu.hust.project.crossplatform.repository.mysql.model.AbsenceRequestModel;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AbsenceRequestAdapter implements IAbsenceRequestPort {
    private final IAbsenceRequestRepository absenceRequestRepository;

    public AbsenceRequestDto createAbsenceRequest(AbsenceRequestDto absenceDto){
        try{
            return AbsenceModelMapper.INSTANCE.toEntity(
                    absenceRequestRepository.save(
                            AbsenceModelMapper.INSTANCE.toModel(absenceDto)
                    )
            );
        }
        catch(Exception e){
            log.error("create new absence request error: {}", e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

    public void reviewAbsenceRequest(ReviewAbsenceRequest request){
        try {
            var existAbsenceRequest = getAbsenceRequestModel(request.getRequestId());
            existAbsenceRequest.setStatus(request.getStatus());
            absenceRequestRepository.save(existAbsenceRequest);
        }
        catch(Exception e){
            log.error("get absence request error: {}", e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

    public List<AbsenceRequestDto> getAbsenceRequests(GetAbsenceRequests request, Integer classId){
        List<AbsenceRequestModel> models;
        if(request.getDate() == null){
            models = absenceRequestRepository.findAbsenceRequestsByClassAndStatus(classId, request.getStatus());
        }
        else {
            models = absenceRequestRepository.findAbsenceRequestsByClassAndStatusAndDate(
                    classId, request.getStatus(), request.getDate()
            );
        }
        return models.stream()
                .map(AbsenceModelMapper.INSTANCE::toEntity)
                .toList();
    }

    public List<AbsenceRequestDto> getAbsenceRequests(List<Integer> requestIds){
        return absenceRequestRepository.findAllById(requestIds).stream()
                .map(AbsenceModelMapper.INSTANCE::toEntity)
                .toList();
    }

    public AbsenceRequestDto getAbsenceRequest(Integer requestId){
        return AbsenceModelMapper.INSTANCE.toEntity(getAbsenceRequestModel(requestId));
    }

    private AbsenceRequestModel getAbsenceRequestModel(Integer requestId){
        return absenceRequestRepository.findById(requestId)
                .orElseThrow(() -> {
                    log.error("getAbsenceRequest error: request id {} not found", requestId);
                    return new ApplicationException("absence request not found");
                });
    }
}
