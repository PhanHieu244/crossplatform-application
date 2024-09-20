package vn.edu.hust.project.crossplatform.exception;

import vn.edu.hust.project.crossplatform.constant.ResponseCode;
import vn.edu.hust.project.crossplatform.exception.base.ApplicationException;

public class PasswordLengthException extends ApplicationException {
    public PasswordLengthException() {
        super(ResponseCode.INVALID_PASSWORD_LENGTH);
    }
}
