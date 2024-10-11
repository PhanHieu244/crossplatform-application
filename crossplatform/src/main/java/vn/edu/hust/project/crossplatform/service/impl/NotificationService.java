package vn.edu.hust.project.crossplatform.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.constant.NotificationStatus;
import vn.edu.hust.project.crossplatform.constant.ResponseCode;
import vn.edu.hust.project.crossplatform.dto.NotificationDto;
import vn.edu.hust.project.crossplatform.dto.request.GetNotificationsRequest;
import vn.edu.hust.project.crossplatform.dto.request.MarkNotificationAsReadRequest;
import vn.edu.hust.project.crossplatform.dto.request.SendNotificationRequest;
import vn.edu.hust.project.crossplatform.exception.base.ApplicationException;
import vn.edu.hust.project.crossplatform.mapper.NotificationMapper;
import vn.edu.hust.project.crossplatform.port.INotificationPort;
import vn.edu.hust.project.crossplatform.service.IAuthService;
import vn.edu.hust.project.crossplatform.service.INotificationService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService implements INotificationService {
    private final IAuthService authService;
    private final INotificationPort notificationPort;

    public void sendNotification(SendNotificationRequest request){
        var account = authService.getAccountByToken(request.getToken());
        var entity = NotificationMapper.INSTANCE.requestToNotificationDto(request);
        entity.setStatus(NotificationStatus.UNREAD);
        entity.setFromUser(account.getId());
        notificationPort.sendNotification(entity);
    }

    public Integer getUnreadNotificationCount(String token){
        var account = authService.getAccountByToken(token);
        return notificationPort.getUnreadNotificationCount(account.getId());
    }

    public void markNotificationAsRead(MarkNotificationAsReadRequest request){
        var account = authService.getAccountByToken(request.getToken());
        notificationPort.markNotificationAsRead(request.getNotificationIds(), account.getId());
    }

    public List<NotificationDto> getNotifications(GetNotificationsRequest request){
        if(request.getIndex() < 0){
            log.error("getNotifications index less than 0");
            throw new ApplicationException(ResponseCode.PARAMETER_VALUE_IS_INVALID ,"index must be greater than 0");
        }
        var account = authService.getAccountByToken(request.getToken());
        return notificationPort.getNotifications(request.getIndex(), request.getCount(), account.getId());
    }
}
