package vn.edu.hust.project.crossplatform.repository.mysql.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.constant.NotificationStatus;
import vn.edu.hust.project.crossplatform.dto.NotificationDto;
import vn.edu.hust.project.crossplatform.exception.base.ApplicationException;
import vn.edu.hust.project.crossplatform.port.INotificationPort;
import vn.edu.hust.project.crossplatform.repository.mysql.INotificationRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.mapper.NotificationModelMapper;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationAdapter implements INotificationPort {
    private final INotificationRepository notificationRepository;

    public void sendNotification(NotificationDto notification) {
        notificationRepository.save(NotificationModelMapper.INSTANCE.toModel(notification));
    }

    public Integer getUnreadNotificationCount(Integer toUser) {
        return getNotificationCount(toUser, NotificationStatus.UNREAD);
    }

    public Integer getNotificationCount(Integer toUser, NotificationStatus status){
        return notificationRepository.countByToUserAndStatus(toUser, status);
    }

    public void markNotificationAsRead(List<Integer> notificationIds, Integer toUser) {
        var notifications = notificationRepository.findAllById(notificationIds);
        notifications.forEach(notification -> {
            if(!notification.getToUser().equals(toUser)) return;
            notification.setStatus(NotificationStatus.READ);
        });
        notificationRepository.saveAll(notifications);
    }

    public List<NotificationDto> getNotifications(Integer index, Integer count, Integer toUser){
        try {
            return notificationRepository.findByToUser(toUser, index, count)
                    .stream()
                    .map(NotificationModelMapper.INSTANCE::toEntity)
                    .toList();
        } catch (Exception e){
            log.error("get notifications err: ", e);
            throw new ApplicationException("get notifications err " + e.getMessage());
        }

    }

}
