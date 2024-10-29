package vn.edu.hust.project.crossplatform.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    private int id;
    private String ho;
    private String ten;
    private String username;
    private String token;
    private String Avatar;
    private String active;
    private String role;
    private List<String> classList;

    // Constructor, Getters và Setters
}
