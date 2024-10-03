package vn.edu.hust.project.crossplatform.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Lecturer;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
    Lecturer findByAccountId(int account_id);
    Lecturer findByAccount(Account id);;
}