package vn.edu.hust.project.crossplatform.exception;

import vn.edu.hust.project.crossplatform.exception.base.HttpFilterException;

public class UnauthorizedException extends HttpFilterException {
    public UnauthorizedException() {
        super("Unauthorized");
    }
}
