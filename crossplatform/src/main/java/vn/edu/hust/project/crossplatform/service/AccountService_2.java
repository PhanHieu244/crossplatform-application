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
}

