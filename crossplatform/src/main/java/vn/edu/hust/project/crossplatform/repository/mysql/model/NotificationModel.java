package vn.edu.hust.project.crossplatform.repository.mysql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import vn.edu.hust.project.crossplatform.constant.NotificationStatus;
import vn.edu.hust.project.crossplatform.constant.NotificationType;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "notifications")
@EntityListeners(AuditingEntityListener.class)
public class NotificationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "recipient_id")
    private Integer recipientId;

    @NotNull
    @Lob
    @Column(name = "message", nullable = false)
    private String message;

    @CreationTimestamp
    @Column(name = "sent_time", nullable = false)
    private LocalDateTime sentTime;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "status", nullable = false)
    private NotificationStatus status;

    @NotNull
    @Column(name = "from_user", nullable = false)
    private Integer fromUser;

    @NotNull
    @Column(name = "to_user", nullable = false)
    private Integer toUser;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private NotificationType type;

}