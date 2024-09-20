package vn.edu.hust.project.crossplatform.exception;

import vn.edu.hust.project.crossplatform.constant.ResponseCode;
import vn.edu.hust.project.crossplatform.exception.base.ApplicationException;

public class EmailFormatException extends ApplicationException {

    public EmailFormatException() {
        super(ResponseCode.INVALID_EMAIL_FORMAT);
    }
}
