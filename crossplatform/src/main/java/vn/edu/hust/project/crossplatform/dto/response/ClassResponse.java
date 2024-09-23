package vn.edu.hust.project.crossplatform.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClassResponse {
    private String message;
    private int statusCode;
    private int Lecturer;

    public ClassResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
