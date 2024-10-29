package vn.edu.hust.project.crossplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hust.project.crossplatform.dto.response.ApiResponse;
import vn.edu.hust.project.crossplatform.dto.request.SignupRequest;
import vn.edu.hust.project.crossplatform.service.AccountService;
import vn.edu.hust.project.crossplatform.service.VerificationService;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/it4788")
public class AuthController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private VerificationService verificationService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignupRequest signupRequest) {
        // Kiểm tra format email
        if (!isValidEmail(signupRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(9995, "Invalid email format"));
        }

        // Kiểm tra miền email
        String email = signupRequest.getEmail();
        if (!email.matches(".*@(?:hust\\.edu\\.vn|soict\\.hust\\.edu\\.vn)$")) {
            return new ResponseEntity<>("1004 | Invalid domain, only hust.edu.vn or soict.hust.edu.vn is allowed", HttpStatus.BAD_REQUEST);
        }

        // Kiểm tra độ dài mật khẩu, trùng với email và ký tự đặc biệt
        String password = signupRequest.getPassword();
        if (password.length() < 6 || password.length() > 15) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(9994, "Password length must be between 6 and 10 characters"));
        }
        if (password.equals(email)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(9995, "Password should not be the same as email"));
        }
        if (!Pattern.matches("^[a-zA-Z0-9]*$", password)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(9995, "Password should not contain special characters"));
        }

        // Check if email already exists
        if (accountService.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse(9996, "User existed"));
        }

        // Đăng kí tài khoản
        Account verificationCode = accountService.registerNewAccount(signupRequest);
        // Xử lý logic tạo mã xác thực
        String verifyCode = verificationService.generateVerifyCode(signupRequest.getEmail());
        verificationCode.setToken(verifyCode); // Sinh mã token
        return ResponseEntity.ok(new ApiResponse(1000, "OK - Verification token sent: " + verifyCode));
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(emailRegex);
    }
}