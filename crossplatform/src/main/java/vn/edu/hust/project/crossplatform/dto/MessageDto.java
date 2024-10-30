package vn.edu.hust.project.crossplatform.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.edu.hust.project.crossplatform.constant.MessageStatus;
import vn.edu.hust.project.crossplatform.utils.MessageStatusSerializer;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MessageDto extends BaseDto{
    private UserDto sender;

    private UserDto receiver;

    private Integer conversationId;

    private String content;

    private LocalDateTime createdAt;

    @JsonSerialize(using = MessageStatusSerializer.class)
    private MessageStatus messageStatus;
}
