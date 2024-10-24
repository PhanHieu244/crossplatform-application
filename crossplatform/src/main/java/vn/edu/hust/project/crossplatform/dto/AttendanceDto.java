package vn.edu.hust.project.crossplatform.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.edu.hust.project.crossplatform.constant.AttendanceStatus;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AttendanceDto extends BaseDto{
    private AttendanceStatus attendanceStatus;
    private LocalDate attendanceTime;
    private Integer classDetailId;
}
