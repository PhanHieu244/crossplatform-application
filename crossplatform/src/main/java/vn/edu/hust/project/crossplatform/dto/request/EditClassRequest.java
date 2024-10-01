package vn.edu.hust.project.crossplatform.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.hust.project.crossplatform.constant.ClassStatus;
import vn.edu.hust.project.crossplatform.constant.ClassType;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EditClassRequest extends TokenRequest {
    @NotNull(message = "class code cant be null")
    private String code;
    private String className;
    private ClassType classType;
    private LocalDate startDate;
    private LocalDate endDate;
    private ClassStatus status;
}
