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

    public ApplicationException(String message){
        this.message = message;
        this.code = (long) HttpStatus.BAD_REQUEST.value();
    }

    public ApplicationException(ResponseCode responseCode){
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }
}
