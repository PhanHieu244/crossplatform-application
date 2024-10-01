package vn.edu.hust.project.crossplatform.exception;

import vn.edu.hust.project.crossplatform.constant.ResponseCode;
import vn.edu.hust.project.crossplatform.exception.base.HttpFilterException;

public class UnauthorizedException extends HttpFilterException {
    public UnauthorizedException() {
        super(ResponseCode.NOT_ACCESS);
    }
    public UnauthorizedException(Object error) {
        super(ResponseCode.NOT_ACCESS, error);
    }
}
