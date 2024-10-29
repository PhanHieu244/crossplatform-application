package vn.edu.hust.project.crossplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hust.project.crossplatform.dto.request.ChangePasswordRequest;
import vn.edu.hust.project.crossplatform.dto.request.LogoutRequest;
import vn.edu.hust.project.crossplatform.dto.response.ApiResponse;
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
                return new ResponseEntity<>("9995 | Token is invalid", HttpStatus.UNAUTHORIZED);
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

    @PostMapping("/change_password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {

        // Kiểm tra token và xác thực tài khoản
        Account account = accountService.findByToken(request.getToken());
        if (account == null) {
            return new ResponseEntity<>("9995 | Token is invalid", HttpStatus.UNAUTHORIZED);
        }

        // Kiểm tra mật khẩu cũ có đúng không
        if (!accountService.checkPassword(account, request.getOldPassword())) {
            return new ResponseEntity<>("1001 | Old password is incorrect", HttpStatus.BAD_REQUEST);
        }

        // Kiểm tra mật khẩu mới không hợp lệ (ví dụ: độ dài, ký tự đặc biệt, giống mật khẩu cũ)
        if (request.getNewPassword().length() < 6 || request.getNewPassword().length() > 10) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(9994, "Password length must be between 6 and 10 characters"));
        }

        // Kiểm tra mật khẩu mới gần giống mật khẩu cũ
        if (isPasswordTooSimilar(request.getOldPassword(), request.getNewPassword())) {
            return new ResponseEntity<>("1003 | New password is too similar to the old one", HttpStatus.BAD_REQUEST);
        }

        // Đổi mật khẩu
        try {
            accountService.changePassword(account, request.getNewPassword());
            return new ResponseEntity<>("1000 | Password changed successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("1004 | Cannot connect to database", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    // Hàm kiểm tra mật khẩu mới có quá giống mật khẩu cũ không
    private boolean isPasswordTooSimilar(String oldPassword, String newPassword) {
        // Tính toán xâu con chung dài nhất (LCS)
        int lcsLength = getLongestCommonSubsequenceLength(oldPassword, newPassword);
        return (lcsLength * 100 / newPassword.length()) >= 80;
    }

    // Hàm tính xâu con chung dài nhất (LCS) giữa 2 chuỗi
    private int getLongestCommonSubsequenceLength(String a, String b) {
        int m = a.length();
        int n = b.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}

