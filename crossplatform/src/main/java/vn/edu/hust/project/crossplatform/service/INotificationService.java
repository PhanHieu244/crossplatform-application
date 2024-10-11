package vn.edu.hust.project.crossplatform.service;

import vn.edu.hust.project.crossplatform.dto.NotificationDto;
import vn.edu.hust.project.crossplatform.dto.request.GetNotificationsRequest;
import vn.edu.hust.project.crossplatform.dto.request.MarkNotificationAsReadRequest;
import vn.edu.hust.project.crossplatform.dto.request.SendNotificationRequest;

import java.util.List;

public interface INotificationService {
    void sendNotification(SendNotificationRequest request);
    Integer getUnreadNotificationCount(String token);
    void markNotificationAsRead(MarkNotificationAsReadRequest request);
    List<NotificationDto> getNotifications(GetNotificationsRequest request);
}
