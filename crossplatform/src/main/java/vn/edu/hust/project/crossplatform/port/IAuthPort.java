package vn.edu.hust.project.crossplatform.port;

import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;

public interface IAuthPort {
    Account getAccountByToken(String token);
    Account getAccountById(Integer id);
    void checkRole(Account account, Account.Role role);
    void checkRole(Account account, Account.Role... roles);
}
