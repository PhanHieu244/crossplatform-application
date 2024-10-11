package vn.edu.hust.project.crossplatform.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.edu.hust.project.crossplatform.constant.NotificationStatus;
import vn.edu.hust.project.crossplatform.constant.NotificationType;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NotificationResponse {
    private Integer id;

    private String message;

    private NotificationStatus status;

    private NotificationType type;

    private Integer recipientId;

    private LocalDateTime sentTime;
}
