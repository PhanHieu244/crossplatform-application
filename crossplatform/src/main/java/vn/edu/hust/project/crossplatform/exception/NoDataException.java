package vn.edu.hust.project.crossplatform.exception;

import vn.edu.hust.project.crossplatform.constant.ResponseCode;
import vn.edu.hust.project.crossplatform.exception.base.ApplicationException;

public class NoDataException extends ApplicationException {
    public NoDataException(Object message) {
        super(ResponseCode.NO_DATA_OR_END_OF_LIST_DATA, message);
    }
}
