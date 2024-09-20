package vn.edu.hust.project.crossplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hust.project.crossplatform.dto.ApiResponse;
import vn.edu.hust.project.crossplatform.dto.SignupRequest;
import vn.edu.hust.project.crossplatform.service.AccountService;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignupRequest signupRequest) {
        // Validate email format
        if (!isValidEmail(signupRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(9995, "Invalid email format"));
        }

        // Validate password length
        if (signupRequest.getPassword().length() < 6 || signupRequest.getPassword().length() > 10) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(9994, "Password length must be between 6 and 10 characters"));
        }

        // Check if email already exists
        if (accountService.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse(9996, "User existed"));
        }

        // Register new account
        String verificationCode = accountService.registerNewAccount(signupRequest);
        return ResponseEntity.ok(new ApiResponse(1000, "OK", verificationCode));
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(emailRegex);
    }
}
