package vn.edu.hust.project.crossplatform.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;

@Repository
public interface AccountRepository_2 extends JpaRepository<Account, Long> {

    // Truy vấn tìm kiếm tài khoản qua token
    Account findByToken(String token);
    Account findById(Integer id);
}