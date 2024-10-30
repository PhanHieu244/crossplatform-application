package vn.edu.hust.project.crossplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hust.project.crossplatform.dto.response.ApiResponse;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;
import vn.edu.hust.project.crossplatform.service.AccountService_2;
import vn.edu.hust.project.crossplatform.dto.response.GetUserInfoResponse;
import vn.edu.hust.project.crossplatform.dto.request.GetUserInfoRequest;
import vn.edu.hust.project.crossplatform.dto.request.SetUserInfoRequest;
@RestController
@RequestMapping("/it4788")
public class AccountController {

    @Autowired
    private AccountService_2 accountService;

    @PostMapping("/get_user_info")
    public ResponseEntity<?> getUserInfo(@RequestBody GetUserInfoRequest request) {
        // Kiểm tra token có hợp lệ không
        Account account = accountService.findByToken(request.getToken());
        if (account == null) {
            return new ResponseEntity<>("9995 | Token is invalid", HttpStatus.UNAUTHORIZED);
        }

        // Kiểm tra trạng thái tài khoản
        if (account.getStatus().equals("Bị khóa")) {
            return new ResponseEntity<>("Account is locked", HttpStatus.FORBIDDEN);
        }

        // Lấy thông tin người dùng cần truy vấn (nếu không truyền userId thì lấy chính người dùng)
        Integer userId = (request.getUserId() != null) ? request.getUserId() : account.getId();

        Account targetAccount = accountService.findById(userId);
        if (targetAccount == null) {
            return new ResponseEntity<>("9995 | User not found", HttpStatus.NOT_FOUND);
        }

        // Bước 4: Kiểm tra trạng thái tài khoản của user được truy vấn
        if (targetAccount.getStatus().equals("Bị khóa")) {
            return new ResponseEntity<>("9995 | User not found", HttpStatus.NOT_FOUND);
        }

        // Tạo dữ liệu trả về
        GetUserInfoResponse.AccountData data = new GetUserInfoResponse.AccountData(
                targetAccount.getId(),
                targetAccount.getHo(),
                targetAccount.getTen(),
                targetAccount.getName(),
                targetAccount.getEmail(),
                targetAccount.getRole(),
                targetAccount.getStatus(),
                targetAccount.getAvatar()
        );

        return new ResponseEntity<>(new GetUserInfoResponse(1000, "OK", data), HttpStatus.OK);
    }


    @PostMapping("/change_info_after_signup")
    public ResponseEntity<?> setUserInfo(@RequestBody SetUserInfoRequest request) {
        // Kiểm tra token hợp lệ
        Account account = accountService.findByToken(request.getToken());
        if (account == null) {
            return new ResponseEntity<>("9995 | Token is invalid", HttpStatus.UNAUTHORIZED);
        }

        // Kiểm tra trạng thái tài khoản
        if (account.getStatus().equals("Bị khóa")) {
            return new ResponseEntity<>("Account is locked", HttpStatus.FORBIDDEN);
        }


        // Bước 3: Kiểm tra định dạng userName (ví dụ: không có số, không ký tự đặc biệt)
        if (request.getUserName() != null && !request.getUserName().matches("^[a-zA-Z_]+$")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(9995, "Invalid email format"));
        }

        // Bước 4: Cập nhật thông tin người dùng

        account = accountService.updateUserInfo(account, request.getUserName(), request.getAvatar());


        // Bước 5: Tạo dữ liệu trả về
        GetUserInfoResponse.AccountData data = new GetUserInfoResponse.AccountData(
                account.getId(),
                account.getHo(),
                account.getTen(),
                account.getName(),
                account.getEmail(),
                account.getRole(),
                account.getStatus(),
                account.getAvatar()
        );

        return new ResponseEntity<>(new GetUserInfoResponse(1000, "OK", data), HttpStatus.OK);
    }
}
