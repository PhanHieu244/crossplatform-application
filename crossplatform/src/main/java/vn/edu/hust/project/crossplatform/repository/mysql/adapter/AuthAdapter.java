package vn.edu.hust.project.crossplatform.repository.mysql.adapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.exception.TokenInvalidException;
import vn.edu.hust.project.crossplatform.exception.UnauthorizedException;
import vn.edu.hust.project.crossplatform.port.IAuthPort;
import vn.edu.hust.project.crossplatform.repository.mysql.AccountRepository_2;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;

@Service
@Slf4j
public class AuthAdapter implements IAuthPort {
    @Autowired
    private AccountRepository_2 accountRepository;

    @Override
    public Account getAccountByToken(String token) {
        Account account = accountRepository.findByToken(token);
        if(account == null) throw new TokenInvalidException();
        return account;
    }

    @Override
    public void checkRole(Account account, Account.Role role) {
        checkAccountRole(account, role);
    }

    public static void checkAccountRole(Account account, Account.Role role) {
        if(!account.getRole().equals(role.toString())) {
            log.error("cant pass check role");
            throw new UnauthorizedException("your role is not allowed");
        }
    }

    @Override
    public void checkRole(Account account, Account.Role... roles) {
        for(Account.Role role : roles) checkRole(account, role);
    }
}
