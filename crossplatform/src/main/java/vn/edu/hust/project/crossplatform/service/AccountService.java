package vn.edu.hust.project.crossplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.dto.request.SignupRequest;
import vn.edu.hust.project.crossplatform.repository.mysql.AccountRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.LecturerRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.StudentRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;


import java.util.Random;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;


    public boolean existsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }

    public Account registerNewAccount(SignupRequest signupRequest) {
        // Tạo account mới
        Account newAccount = new Account();
        newAccount.setHo(signupRequest.getHo());
        newAccount.setTen(signupRequest.getTen());
        newAccount.setEmail(signupRequest.getEmail());
        newAccount.setPassword(signupRequest.getPassword());  // Mã hóa mật khẩu
        newAccount.setName(signupRequest.getEmail());  // Name có thể là email hoặc tên đầy đủ
        newAccount.setRole(signupRequest.getRole().toString()); // Đặt role từ request
        newAccount.setStatus("Bị khóa"); // Trạng thái mặc định là 'Kích hoạt'
        newAccount.setToken(null); // Sinh mã token

        Account savedAccount = accountRepository.save(newAccount);



        // Trả về mã xác thực
        return savedAccount;
    }


    private String encodePassword(String password) {
        // Sử dụng BCryptPasswordEncoder hoặc một cơ chế mã hóa tương tự
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }


}