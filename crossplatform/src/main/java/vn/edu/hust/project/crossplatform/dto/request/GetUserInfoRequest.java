package vn.edu.hust.project.crossplatform.dto.request;

import lombok.Data;

@Data
public class GetUserInfoRequest {
    private String token;
    private Integer userId;
}

