package vn.edu.hust.project.crossplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.dto.request.SignupRequest;
import vn.edu.hust.project.crossplatform.repository.mysql.AccountRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.LecturerRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.StudentRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Lecturer;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Student;

import java.util.Random;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private StudentRepository studentRepository;

    public boolean existsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }

    public String registerNewAccount(SignupRequest signupRequest) {
        // Tạo account mới
        Account newAccount = new Account();
        newAccount.setEmail(signupRequest.getEmail());
        newAccount.setPassword(encodePassword(signupRequest.getPassword()));  // Mã hóa mật khẩu
        newAccount.setName(signupRequest.getEmail());  // Name có thể là email hoặc tên đầy đủ
        newAccount.setRole(signupRequest.getRole()); // Đặt role từ request
        newAccount.setStatus("Kích hoạt"); // Trạng thái mặc định là 'Kích hoạt'
        newAccount.setToken(generateVerificationCode()); // Sinh mã token

        Account savedAccount = accountRepository.save(newAccount);

        // Phân biệt role
        if (savedAccount.getRole().equalsIgnoreCase("LECTURER")) {
            Lecturer lecturer = new Lecturer();
            lecturer.setAccount(savedAccount);
            lecturer.setEmail(savedAccount.getEmail());
            lecturer.setName(savedAccount.getName());
            lecturerRepository.save(lecturer);
        } else if (savedAccount.getRole().equalsIgnoreCase("STUDENT")) {
            Student student = new Student();
            student.setAccount(savedAccount);
            student.setEmail(savedAccount.getEmail());
            student.setName(savedAccount.getName());
            studentRepository.save(student);
        }

        // Trả về mã xác thực
        return savedAccount.getToken();
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

    private String encodePassword(String password) {
        // Sử dụng BCryptPasswordEncoder hoặc một cơ chế mã hóa tương tự
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}