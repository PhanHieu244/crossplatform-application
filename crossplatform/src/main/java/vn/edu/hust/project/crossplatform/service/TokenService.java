package vn.edu.hust.project.crossplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.repository.mysql.AccountRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.AccountRepository_2;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;

@Service
public class TokenService {

    @Autowired
    private AccountRepository_2 accountRepository;

    public Account validateToken(String token) throws Exception {
        Account account = accountRepository.findByToken(token);

        if (account == null) {
            throw new Exception("Token không hợp lệ hoặc đã hết hạn.");
        }

        // Kiểm tra vai trò của tài khoản là giảng viên


        return account;
    }
}

