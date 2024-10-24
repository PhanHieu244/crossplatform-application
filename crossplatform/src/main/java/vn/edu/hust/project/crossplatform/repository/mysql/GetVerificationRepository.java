package vn.edu.hust.project.crossplatform.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;

@Repository
public interface GetVerificationRepository extends JpaRepository<Account, Long> {

    // Tìm tài khoản qua email
    Account findByEmail(String email);

    // Kiểm tra nếu email đã được kích hoạt
    boolean existsByEmailAndStatus(String email, String status);
}
