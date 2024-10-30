package vn.edu.hust.project.crossplatform.dto.request;

import lombok.Data;

@Data
public class SetUserInfoRequest {
    private String token;
    private String userName;
    private String avatar;
}
