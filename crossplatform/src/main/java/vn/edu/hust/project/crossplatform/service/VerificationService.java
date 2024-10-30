package vn.edu.hust.project.crossplatform.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.repository.mysql.GetVerificationRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.LecturerRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.StudentRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Lecturer;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Student;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class VerificationService {
    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private GetVerificationRepository accountRepository;
    private final Map<String, VerificationData> verificationCodes = new ConcurrentHashMap<>();

    // Bảng tạm 2: Lưu thời gian gửi request gần nhất cho từng email
    private final Map<String, Long> verificationRequests = new ConcurrentHashMap<>();

    // Class lưu trữ mã xác thực và thời gian hết hạn
    @Data
    @AllArgsConstructor
    public static class VerificationData {
        private String code;
        private long expiresAt;
    }

    // Kiểm tra nếu email đã đăng ký trong hệ thống
    public boolean isEmailRegistered(String email) {
        return accountRepository.findByEmail(email) != null; // Trả về true nếu email tồn tại
    }

    public boolean checkPassword(String email, String password) {
        Account account = accountRepository.findByEmail(email); // Trả về true nếu email tồn tại
        if(account.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    // Kiểm tra nếu email đã được xác thực (trạng thái 'Kích hoạt')
    public boolean isEmailVerified(String email) {
        return accountRepository.existsByEmailAndStatus(email, "Kích hoạt");
    }

    // Kiểm tra tần suất gửi request (giới hạn 120 giây)
    public boolean isRequestTooFrequent(String email) {
        // Lấy thời gian hiện tại
        long currentTime = System.currentTimeMillis();

        // Nếu email đã có thời gian request trước đó thì kiểm tra khoảng cách thời gian
        if (verificationRequests.containsKey(email)) {
            long lastRequestTime = verificationRequests.get(email);
            // Kiểm tra nếu thời gian giữa lần request hiện tại và trước đó dưới 120 giây
            return (currentTime - lastRequestTime) < 120000; // 120,000 milliseconds = 120 giây
        }

        // Nếu chưa có request trước đó, thì không giới hạn
        return false;
    }

    // Tạo mã xác thực cho email và lưu lại trong bộ nhớ tạm
    public String generateVerifyCode(String email) {
        String code = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        long expiresAt = System.currentTimeMillis() + 120 * 1000; // Hết hạn sau 120 giây

        // Lưu mã xác thực và thời gian hết hạn vào bộ nhớ tạm
        verificationCodes.put(email, new VerificationData(code, expiresAt));

        // Lưu thời gian gửi request
        verificationRequests.put(email, System.currentTimeMillis());

        return code;
    }

    public int getUserIdByEmail(String email) {
        Account account = accountRepository.findByEmail(email);
        if (account != null) {
            return account.getId(); // Trả về ID của người dùng
        }
        return -1;
    }

    // Kiểm tra mã xác thực có hợp lệ không (chưa hết hạn và đúng mã)
    public boolean isValidVerificationCode(String email, String code) {
        VerificationData data = verificationCodes.get(email);
        if (data != null) {
            long currentTime = System.currentTimeMillis();
            if (currentTime <= data.getExpiresAt()) {
                return data.getCode().equals(code);
            }
        }
        return false;
    }

    // Xóa mã xác thực sau khi kiểm tra
    public void removeVerificationCode(String email) {
        verificationCodes.remove(email);
    }
    // Phương thức cập nhật trạng thái của tài khoản thành 'Kích hoạt'
    public void activateAccount(String email) {
        // Tìm tài khoản dựa trên email
        Account account = accountRepository.findByEmail(email);
        if (account != null) {
            // Cập nhật trạng thái thành 'Kích hoạt'
            account.setStatus("Kích hoạt");
            accountRepository.save(account); // Lưu lại thay đổi vào database
            // Phân biệt role
            if (account.getRole().equalsIgnoreCase("LECTURER")) {
                Lecturer lecturer = new Lecturer();
                lecturer.setAccount(account);
                lecturer.setEmail(account.getEmail());
                lecturer.setName(account.getName());
                lecturerRepository.save(lecturer);
            } else if (account.getRole().equalsIgnoreCase("STUDENT")) {
                Student student = new Student();
                student.setAccount(account);
                student.setEmail(account.getEmail());
                student.setName(account.getName());
                studentRepository.save(student);
            }
        }
    }
}
