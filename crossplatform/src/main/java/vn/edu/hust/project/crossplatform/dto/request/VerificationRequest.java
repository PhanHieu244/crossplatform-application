package vn.edu.hust.project.crossplatform.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerificationRequest {
    private String email;
    private String password;  // Chỉ dùng khi kiểm tra mã xác thực (check verification)
    private String verifyCode;
}
