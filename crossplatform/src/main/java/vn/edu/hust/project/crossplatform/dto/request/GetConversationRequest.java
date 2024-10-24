package vn.edu.hust.project.crossplatform.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetConversationRequest extends TokenRequest{
    @NotNull
    @Min(value = 0)
    private Integer index;

    @Min(value = 0)
    @NotNull
    private Integer count;

    private Integer partnerId;
    private Integer conversationId;
    private Boolean markAsRead;
}
