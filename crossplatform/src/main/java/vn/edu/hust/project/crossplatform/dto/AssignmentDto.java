package vn.edu.hust.project.crossplatform.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.File;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AssignmentDto extends BaseDto {
    @Size(max = 100, message = "Title must not exceed 100 characters")
    @NotNull(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Lecturer ID is required")
    private Integer lecturerId;

    @NotNull(message = "Class ID is required")
    private Integer classId;

    @NotNull
    private LocalDateTime deadline;

    private File file;
}
