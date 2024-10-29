package vn.edu.hust.project.crossplatform.dto.request;

import lombok.Data;

@Data
public class ChangePasswordRequest {

    private String token;
    private String oldPassword;
    private String newPassword;
}
