package vn.edu.hust.project.crossplatform.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hust.project.crossplatform.dto.request.GetNotificationsRequest;
import vn.edu.hust.project.crossplatform.dto.request.MarkNotificationAsReadRequest;
import vn.edu.hust.project.crossplatform.dto.request.SendNotificationRequest;
import vn.edu.hust.project.crossplatform.dto.request.TokenRequest;
import vn.edu.hust.project.crossplatform.dto.response.Resource;
import vn.edu.hust.project.crossplatform.service.INotificationService;

@RestController
@RequestMapping("/it5023e")
@RequiredArgsConstructor
public class NotificationController {
    private final INotificationService notificationService;

    @PostMapping(value = "/send_notification")
    private ResponseEntity<Resource> sendNotification(
            @Valid @RequestBody SendNotificationRequest request
    ){
        notificationService.sendNotification(request);
        return ResponseEntity.ok().body(
                new Resource("send notification successful")
        );
    }

    @GetMapping(value = "/get_unread_notification_count")
    private ResponseEntity<Resource> getUnreadNotificationCount(
            @Valid @RequestBody TokenRequest token
    ){
        return ResponseEntity.ok().body(
                new Resource(notificationService.getUnreadNotificationCount(token.getToken()))
        );
    }

    @PostMapping(value = "/mark_notification_as_read")
    private ResponseEntity<Resource> markNotificationsAsRead(
            @Valid @RequestBody MarkNotificationAsReadRequest request
    ){
        notificationService.markNotificationAsRead(request);
        return ResponseEntity.ok().body(
                new Resource("mark notifications as read successful")
        );
    }

    @PostMapping(value = "/get_notifications")
    private ResponseEntity<Resource> getNotifications(
            @Valid @RequestBody GetNotificationsRequest request
    ){
        return ResponseEntity.ok().body(
                new Resource(notificationService.getNotifications(request))
        );
    }
}
