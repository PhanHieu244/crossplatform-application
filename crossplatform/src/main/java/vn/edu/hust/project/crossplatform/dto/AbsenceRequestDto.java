package vn.edu.hust.project.crossplatform.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import vn.edu.hust.project.crossplatform.constant.AbsenceRequestStatus;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AbsenceRequestDto extends BaseDto {
    @NotNull
    private Integer classDetailId;
    @NotNull
    private LocalDate absenceDate;
    @NotNull
    private String reason;
    @NotNull
    private AbsenceRequestStatus status;
}
