package vn.edu.hust.project.crossplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.repository.mysql.AccountRepository_1;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class AccountService_1 {

    @Autowired
    private AccountRepository_1 accountRepository;

    // Hàm tìm kiếm tài khoản theo email và mật khẩu
    public Account findByEmailAndPassword(String email, String password) {
        return accountRepository.findByEmailAndPassword(email, password);
    }

    // Hàm tạo token (giả lập)
    public String generateToken(Account account, String deviceId) {
        // Token có thể là JWT hoặc chuỗi mã hóa khác
        return generateVerificationCode();
    }

    // Hàm lấy danh sách lớp học theo role
    public List<String> getClassListByRole(Account.Role role, Long accountId) {
        if (role == Account.Role.LECTURER) {
            return getTeachingClasses(accountId);
        } else {
            return getStudentClasses(accountId);
        }
    }

    private List<String> getTeachingClasses(Long lecturerId) {
        // Lấy danh sách lớp mà giảng viên đang dạy
        // Giả lập dữ liệu, bạn cần thay thế bằng dữ liệu thực tế từ cơ sở dữ liệu
        return List.of("Lớp 1", "Lớp 2");
    }

    private List<String> getStudentClasses(Long studentId) {
        // Lấy danh sách lớp mà sinh viên đã đăng ký
        // Giả lập dữ liệu, bạn cần thay thế bằng dữ liệu thực tế từ cơ sở dữ liệu
        return List.of("Lớp A", "Lớp B");
    }

    // Phương thức lưu tài khoản sau khi cập nhật token hoặc session
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }
    private String generateVerificationCode() {
        int length = 6;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder token = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            token.append(characters.charAt(random.nextInt(characters.length())));
        }
        return token.toString();
    }
}
