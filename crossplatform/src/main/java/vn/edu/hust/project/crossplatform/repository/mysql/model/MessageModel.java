package vn.edu.hust.project.crossplatform.repository.mysql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import vn.edu.hust.project.crossplatform.constant.MessageStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "messages")
@EntityListeners(AuditingEntityListener.class)
public class MessageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "sender", nullable = false)
    private Integer senderId;

    @Column(name = "conversation_id", nullable = false)
    private Integer conversationId;

    @Lob
    @Column(name = "content")
    private String content;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MessageStatus status;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}