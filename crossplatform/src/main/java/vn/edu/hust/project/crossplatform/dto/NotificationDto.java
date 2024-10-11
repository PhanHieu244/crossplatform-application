package vn.edu.hust.project.crossplatform.dto;

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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NotificationDto extends BaseDto {
    @NotNull
    private String message;

    @NotNull
    private NotificationStatus status;

    @NotNull
    private Integer fromUser;

    @NotNull
    private Integer toUser;

    @NotNull
    private NotificationType type;

    private Integer recipientId;

    private LocalDateTime sentTime;
}
