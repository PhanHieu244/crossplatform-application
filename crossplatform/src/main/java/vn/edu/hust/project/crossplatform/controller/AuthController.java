package vn.edu.hust.project.crossplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hust.project.crossplatform.constant.ResponseCode;
import vn.edu.hust.project.crossplatform.dto.response.ApiResponse;
import vn.edu.hust.project.crossplatform.dto.request.SignupRequest;
import vn.edu.hust.project.crossplatform.dto.response.Resource;
import vn.edu.hust.project.crossplatform.exception.EmailFormatException;
import vn.edu.hust.project.crossplatform.exception.PasswordLengthException;
import vn.edu.hust.project.crossplatform.exception.UserExistedException;
import vn.edu.hust.project.crossplatform.exception.base.ApplicationException;
import vn.edu.hust.project.crossplatform.service.AccountService;


@RestController
@RequestMapping("/it4788")
public class AuthController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignupRequest signupRequest) {
        // Validate email format
        if (!isValidEmail(signupRequest.getEmail())) {
            /*return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(9995, "Invalid email format"));*/
            throw new EmailFormatException();
        }

        // Validate password length
        if (signupRequest.getPassword().length() < 6 || signupRequest.getPassword().length() > 10) {
            /*return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(9994, "Password length must be between 6 and 10 characters"));*/
            throw new PasswordLengthException();
        }

        // Check if email already exists
        if (accountService.existsByEmail(signupRequest.getEmail())) {
            /*return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse(9996, "User existed"));*/
            throw new UserExistedException();
        }

        // Register new account
        String verificationCode = accountService.registerNewAccount(signupRequest);
        return ResponseEntity.ok(new Resource(verificationCode));
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(emailRegex);
    }
}
