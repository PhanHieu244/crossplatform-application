package vn.edu.hust.project.crossplatform.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;

@Repository
public interface AccountRepository_1 extends JpaRepository<Account, Long> {

    // Truy vấn tìm kiếm tài khoản theo email và mật khẩu
    Account findByEmailAndPassword(String email, String password);
}
