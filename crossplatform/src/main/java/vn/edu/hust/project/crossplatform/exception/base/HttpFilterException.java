package vn.edu.hust.project.crossplatform.exception.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HttpFilterException extends RuntimeException{
    private Long code;
    private String message;

    public HttpFilterException(String message){
        this.message = message;
        this.code = (long) HttpStatus.UNAUTHORIZED.value();
    }
}

