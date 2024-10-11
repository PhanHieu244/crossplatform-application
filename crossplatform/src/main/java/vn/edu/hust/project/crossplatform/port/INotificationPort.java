package vn.edu.hust.project.crossplatform.port;

import vn.edu.hust.project.crossplatform.dto.NotificationDto;
import vn.edu.hust.project.crossplatform.constant.NotificationStatus;

import java.util.List;

public interface INotificationPort {
    void sendNotification(NotificationDto notification);
    Integer getUnreadNotificationCount(Integer toUser);
    Integer getNotificationCount(Integer toUser, NotificationStatus status);
    void markNotificationAsRead(List<Integer> notificationIds, Integer toUser);
    List<NotificationDto> getNotifications(Integer index, Integer count, Integer toUser);
}
