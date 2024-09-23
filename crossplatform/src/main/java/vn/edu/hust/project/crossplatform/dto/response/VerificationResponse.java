package vn.edu.hust.project.crossplatform.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VerificationResponse {
    private String message;        // Thông báo
    private int userId;           // ID của người dùng
}
