package vn.edu.hust.project.crossplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.repository.mysql.AccountRepository_2;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;

@Service
public class AccountService_2 {

    @Autowired
    private AccountRepository_2 accountRepository;

    // Phương thức tìm tài khoản qua token
    public Account findByToken(String token) {
        return accountRepository.findByToken(token);
    }

    // Phương thức để lưu lại tài khoản sau khi cập nhật token
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    // Kiểm tra mật khẩu
    public boolean checkPassword(Account account, String password) {
        return account.getPassword().equals(password);  // Nên sử dụng hashing cho mật khẩu thực tế
    }

    // Đổi mật khẩu
    public void changePassword(Account account, String newPassword) {
        account.setPassword(newPassword);  // Nên sử dụng hashing cho mật khẩu thực tế
        accountRepository.save(account);
    }

    public Account findById(Integer userId) {
        return accountRepository.findById(userId);
    }

    public Account updateUserInfo(Account account, String userName, String avatar) {
        if (userName != null && !userName.trim().isEmpty()) {
            account.setName(userName);
        }
        if (avatar != null && !avatar.trim().isEmpty()) {
            // Kiểm tra liên kết bị cấm (ví dụ: vnhackers.com)
            if (avatar.contains("vnhackers.com")) {
                throw new IllegalArgumentException("Invalid avatar link");
            }
            account.setAvatar(avatar);
        }
        return accountRepository.save(account);
    }

}

