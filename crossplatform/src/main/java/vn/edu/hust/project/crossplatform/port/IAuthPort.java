package vn.edu.hust.project.crossplatform.port;

import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;

public interface IAuthPort {
    Account getAccountByToken(String token);
    void checkRole(Account account, Account.Role role);
    void checkRole(Account account, Account.Role... roles);
}
