package vn.edu.hust.project.crossplatform.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.hust.project.crossplatform.constant.NotificationType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SendNotificationRequest extends TokenRequest{
    @NotNull
    private String message;

    @NotNull
    private Integer toUser;

    @NotNull
    private NotificationType type;

    private Integer recipientId;
}
