package vn.edu.hust.project.crossplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hust.project.crossplatform.dto.request.LogoutRequest;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;
import vn.edu.hust.project.crossplatform.service.AccountService_2;

@RestController
@RequestMapping("/it4788")
public class LogoutController {

    @Autowired
    private AccountService_2 accountService;

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody LogoutRequest logoutRequest) {
        try {
            // Lấy tài khoản dựa trên token
            Account account = accountService.findByToken(logoutRequest.getToken());

            // Nếu không tìm thấy tài khoản với token này, trả về lỗi
            if (account == null) {
                return new ResponseEntity<>("Token is invalid or user not found", HttpStatus.UNAUTHORIZED);
            }

            // Xóa token của người dùng => Đăng xuất
            account.setToken(null);
            accountService.saveAccount(account); // Lưu cập nhật vào DB

            // Trả về thông báo thành công
            return new ResponseEntity<>("1000 | OK - Logged out successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error during logout: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

