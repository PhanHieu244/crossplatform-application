package vn.edu.hust.project.crossplatform.exception.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import vn.edu.hust.project.crossplatform.constant.ResponseCode;

@AllArgsConstructor
@Getter
@Setter
public class ApplicationException extends RuntimeException{
    private String message;
    private Long code;
    private Object error;

    public ApplicationException(Object error){
        this.message = ResponseCode.EXCEPTION_ERROR.getMessage();
        this.code = ResponseCode.EXCEPTION_ERROR.getCode();
        this.error = error;
    }

    public ApplicationException(ResponseCode responseCode){
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        error = null;
    }

    public ApplicationException(ResponseCode responseCode, Object error){
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.error = error;
    }
}
