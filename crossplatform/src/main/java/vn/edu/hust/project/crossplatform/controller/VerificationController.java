package vn.edu.hust.project.crossplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hust.project.crossplatform.constant.ResponseCode;
import vn.edu.hust.project.crossplatform.dto.request.VerificationRequest;
import vn.edu.hust.project.crossplatform.dto.response.Resource;
import vn.edu.hust.project.crossplatform.dto.response.VerificationResponse;
import vn.edu.hust.project.crossplatform.service.VerificationService;

@RestController
@RequestMapping("/it4788")
public class VerificationController {

    @Autowired
    private VerificationService verificationService;

    // API để lấy mã xác thực
    @PostMapping("/get_verify_code")
    public ResponseEntity<?> getVerifyCode(@RequestBody VerificationRequest verificationRequest) {
        try {

            // Kiểm tra xem email đã được đăng ký chưa
            if (!verificationService.isEmailRegistered(verificationRequest.getEmail())) {
                return new ResponseEntity<>("9995 | Email not registered", HttpStatus.BAD_REQUEST);
            }

            // Kiểm tra xem email đã hoàn thành xác thực chưa
            if (verificationService.isEmailVerified(verificationRequest.getEmail())) {
                return new ResponseEntity<>("1010 | Email already verified", HttpStatus.CONFLICT);
            }

            // Kiểm tra thời gian giữa các request
            if (verificationService.isRequestTooFrequent(verificationRequest.getEmail())) {
                return new ResponseEntity<>("1009 | Request too frequent", HttpStatus.TOO_MANY_REQUESTS);
            }

            // Xử lý logic tạo mã xác thực
            String verifyCode = verificationService.generateVerifyCode(verificationRequest.getEmail());

            // Trả về mã xác thực thành công
            return ResponseEntity.ok().body(new Resource(ResponseCode.OK, verifyCode));

        } catch (Exception e) {
            return new ResponseEntity<>("Error during verification token generation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Hàm kiểm tra định dạng email cơ bản
    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(emailRegex);
    }

    // API để kiểm tra mã xác thực
    @PostMapping("/check_verify_code")
    public ResponseEntity<?> verifyCode(@RequestBody VerificationRequest verificationRequest) {
        // Kiểm tra email hợp lệ
        if (!isValidEmail(verificationRequest.getEmail())) {
            return new ResponseEntity<>("1004 | Invalid email format", HttpStatus.BAD_REQUEST);
        }

        // Kiểm tra nếu không truyền mã xác thực
        if (verificationRequest.getVerifyCode() == null || verificationRequest.getVerifyCode().isEmpty()) {
            return new ResponseEntity<>("1002 | Verification code is missing", HttpStatus.BAD_REQUEST);
        }

        // Kiểm tra email có trong danh sách đăng ký chưa
        if (!verificationService.isEmailRegistered(verificationRequest.getEmail())) {
            return new ResponseEntity<>("9995 | Email not registered", HttpStatus.NOT_FOUND);
        }

        // Kiểm tra nếu email đã được xác thực trước đó
        if (verificationService.isEmailVerified(verificationRequest.getEmail())) {
            return new ResponseEntity<>("1010 | Email already verified", HttpStatus.CONFLICT);
        }

        // Kiểm tra mã xác thực có hợp lệ và đúng với email được gửi
        if (!verificationService.isValidVerificationCode(verificationRequest.getEmail(), verificationRequest.getVerifyCode())) {
            return new ResponseEntity<>("1004 | Invalid verification code or email mismatch", HttpStatus.BAD_REQUEST);
        }

        // Nếu mọi thứ đều hợp lệ, xóa mã xác thực và trả về thông tin đăng nhập
        verificationService.removeVerificationCode(verificationRequest.getEmail());
        verificationService.activateAccount(verificationRequest.getEmail());
        int userId = verificationService.getUserIdByEmail(verificationRequest.getEmail());

        // Trả về mã phiên đăng nhập và ID người dùng
        return new ResponseEntity<>(new VerificationResponse("1000 | OK", userId), HttpStatus.OK);
    }

}
