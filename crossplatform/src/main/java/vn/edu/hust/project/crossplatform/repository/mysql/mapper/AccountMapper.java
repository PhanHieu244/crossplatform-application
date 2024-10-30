package vn.edu.hust.project.crossplatform.repository.mysql.mapper;

import vn.edu.hust.project.crossplatform.dto.UserDto;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;

public class AccountMapper {
    public static final AccountMapper INSTANCE = new AccountMapper();

    private AccountMapper() {}

    public UserDto accountToUserDto(Account account) {
        return UserDto.builder()
                .id(account.getId())
                .name(account.getName())
                .avatar("TODO set avatar")
                .build();
    }
}
