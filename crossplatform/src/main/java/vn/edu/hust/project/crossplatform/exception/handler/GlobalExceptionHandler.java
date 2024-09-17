package vn.edu.hust.project.crossplatform.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vn.edu.hust.project.crossplatform.entity.response.Resource;
import vn.edu.hust.project.crossplatform.exception.base.ApplicationException;
import vn.edu.hust.project.crossplatform.exception.base.HttpFilterException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Resource> handleApplicationException(ApplicationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new Resource(ex.getCode(), ex.getMessage()));
    }

    @ExceptionHandler(HttpFilterException.class)
    public ResponseEntity<Resource> handleUnauthorizedException(HttpFilterException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new Resource(ex.getCode(), ex.getMessage()));
    }
}

