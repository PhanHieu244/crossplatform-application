package vn.edu.hust.project.crossplatform.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
    private String ho;
    private String ten;
    private String email;
    private String password;
    private String uuid;
    private Account.Role role; // lecturer hoặc student
}

