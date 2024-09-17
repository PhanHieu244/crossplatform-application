package vn.edu.hust.project.crossplatform.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    GET_USER_NOT_FOUND(4000001L, "Get user not found"),

    GET_ROLE_NOT_FOUND(4000002L, "Get role not found"),

    EMAIL_IS_EXISTED(4000003L, "Email is existed"),
    ;
    private final long code;
    private final String message;
}
