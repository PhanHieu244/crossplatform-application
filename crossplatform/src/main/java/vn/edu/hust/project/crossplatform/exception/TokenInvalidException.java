package vn.edu.hust.project.crossplatform.exception;

import vn.edu.hust.project.crossplatform.constant.ResponseCode;
import vn.edu.hust.project.crossplatform.exception.base.HttpFilterException;

public class TokenInvalidException extends HttpFilterException {
    public TokenInvalidException() {
        super(ResponseCode.TOKEN_IS_INVALID);
    }
}