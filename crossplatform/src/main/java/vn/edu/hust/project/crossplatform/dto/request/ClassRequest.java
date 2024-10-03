package vn.edu.hust.project.crossplatform.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassRequest {
    private String Lecturer;
    private String token;
    private String role;
    private int user_id;

    @Size(max = 100, message = "Tên lớp học không được vượt quá 100 ký tự.")
    private String className;

    @NotNull
    private String semester;

    private String description;

    @Min(value = 1, message = "Số lượng sinh viên tối thiểu phải là 1.")
    @Max(value = 50)
    private Double maxStudents;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

}


