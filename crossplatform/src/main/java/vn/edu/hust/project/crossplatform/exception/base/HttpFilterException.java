package vn.edu.hust.project.crossplatform.exception.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import vn.edu.hust.project.crossplatform.constant.ResponseCode;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HttpFilterException extends RuntimeException{
    private Long code;
    private String message;
    private Object error;

    public HttpFilterException(String message){
        this.message = message;
        this.code = (long) HttpStatus.UNAUTHORIZED.value();
        error = null;
    }

    public HttpFilterException(ResponseCode responseCode){
        code = responseCode.getCode();
        message = responseCode.getMessage();
        error = null;
    }

    public HttpFilterException(ResponseCode responseCode, Object error){
        code = responseCode.getCode();
        message = responseCode.getMessage();
        this.error = error;
    }
}

