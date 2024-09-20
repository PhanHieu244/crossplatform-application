package vn.edu.hust.project.crossplatform.exception;

import vn.edu.hust.project.crossplatform.constant.ResponseCode;
import vn.edu.hust.project.crossplatform.exception.base.ApplicationException;

public class UserExistedException extends ApplicationException {
    public UserExistedException() {
        super(ResponseCode.USER_EXISTED);
    }
}
