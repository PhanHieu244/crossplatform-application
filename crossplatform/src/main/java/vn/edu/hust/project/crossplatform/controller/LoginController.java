package vn.edu.hust.project.crossplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hust.project.crossplatform.constant.ResponseCode;
import vn.edu.hust.project.crossplatform.dto.request.LoginRequest;
import vn.edu.hust.project.crossplatform.dto.response.LoginResponse;
import vn.edu.hust.project.crossplatform.exception.base.ApplicationException;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;
import vn.edu.hust.project.crossplatform.service.AccountService_1;

import java.util.List;

@RestController
@RequestMapping("/it4788")
public class LoginController {

    @Autowired
    private AccountService_1 accountService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Kiểm tra xem các trường email, mật khẩu và deviceId có được cung cấp không
        if (loginRequest.getEmail().isBlank() || loginRequest.getPassword().isBlank() || loginRequest.getDeviceId().isBlank()) {
            return new ResponseEntity<>("Missing required parameters", HttpStatus.BAD_REQUEST);
        }

        // Kiểm tra email định dạng hợp lệ hay không
        if (!isValidEmail(loginRequest.getEmail())) {
            return new ResponseEntity<>("9995 | Invalid email/password format", HttpStatus.BAD_REQUEST);
        }

        try {
            // Tìm kiếm tài khoản bằng email và mật khẩu
            Account account = accountService.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());

            // Nếu không tìm thấy tài khoản
            if (account == null) {
                return new ResponseEntity<>("User not found or wrong password", HttpStatus.UNAUTHORIZED);
            }

            // Nếu tài khoản bị khóa
            if (account.getStatus().equals("Bị khóa")) {
                return new ResponseEntity<>("Account is locked", HttpStatus.FORBIDDEN);
            }

            if (account.getToken() != null) {
                // Token đã tồn tại => Phiên đăng nhập cũ đã có
                // Xóa token cũ (ghi đè với token mới)
                account.setToken(null); // Xóa token cũ

                // Có thể ghi log hoặc thông báo rằng token cũ đã bị xóa
                System.out.println("Old session from another device has been terminated.");
            }

            // Tạo token mới và lưu lại
            String newToken = accountService.generateToken(account, loginRequest.getDeviceId());
            account.setToken(newToken); // Lưu token mới
            account.setSession(loginRequest.getDeviceId()); // Lưu session (nếu cần)

            // Tạo token cho phiên đăng nhập
            String token = accountService.generateToken(account, loginRequest.getDeviceId());
            account.setToken(token);

            accountService.saveAccount(account);

            // Chuẩn bị danh sách lớp học của sinh viên hoặc giảng viên
            Account.Role role = Account.Role.valueOf(account.getRole());  // Chuyển từ String sang Enum
            Long accountId = account.getId().longValue();  // Chuyển từ Integer sang Long

            List<String> classList = accountService.getClassListByRole(role, accountId);


            // Tạo đối tượng phản hồi
            LoginResponse response = new LoginResponse(
                    account.getId(),
                    account.getName(),
                    account.getToken(),
                    account.getStatus().toString(),
                    account.getRole().toString(),
                    classList
            );

            // Trả về phản hồi thành công
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return new ResponseEntity<>("Error during login: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(emailRegex);
    }
}
