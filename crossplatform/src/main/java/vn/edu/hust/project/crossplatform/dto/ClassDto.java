package vn.edu.hust.project.crossplatform.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.edu.hust.project.crossplatform.constant.ClassStatus;
import vn.edu.hust.project.crossplatform.constant.ClassType;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ClassDto extends BaseDto {
    private String classId;
    private String className;
    private String schedule;
    private Integer lecturerId;
    private Integer maxStudentAmount;
    private String attachedCode;
    private ClassType classType;
    private LocalDate startDate;
    private LocalDate endDate;
    private ClassStatus status;
}
