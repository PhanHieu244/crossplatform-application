package vn.edu.hust.project.crossplatform.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import vn.edu.hust.project.crossplatform.constant.ClassStatus;

import java.time.LocalDate;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ClassInfoResponse {
    private String classId;
    private String className;
    private String lecturerName;
    private Integer studentCount;
    private LocalDate startDate;
    private LocalDate endDate;
    private ClassStatus status;
}
