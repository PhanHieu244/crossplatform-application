package vn.edu.hust.project.crossplatform.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.edu.hust.project.crossplatform.constant.MessageStatus;
import vn.edu.hust.project.crossplatform.dto.UserDto;
import vn.edu.hust.project.crossplatform.utils.MessageStatusSerializer;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MessageResponse {
    private Integer messageId;
    private String message;
    private UserDto sender;
    private LocalDateTime createdAt;
    @JsonSerialize(using = MessageStatusSerializer.class)
    private MessageStatus unread;
}
