package vn.edu.hust.project.crossplatform.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetUserInfoResponse {
    private int code;
    private String message;
    private AccountData data;

    @Data
    @AllArgsConstructor
    public static class AccountData {
        private Integer id;
        private String ho;
        private String ten;
        private String name;
        private String email;
        private String role;
        private String status;
        private String avatar;
    }
}
