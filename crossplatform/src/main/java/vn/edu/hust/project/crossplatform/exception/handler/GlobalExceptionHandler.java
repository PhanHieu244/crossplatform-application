package vn.edu.hust.project.crossplatform.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vn.edu.hust.project.crossplatform.constant.ResponseCode;
import vn.edu.hust.project.crossplatform.dto.response.Resource;
import vn.edu.hust.project.crossplatform.exception.base.ApplicationException;
import vn.edu.hust.project.crossplatform.exception.base.HttpFilterException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Resource> handleApplicationException(ApplicationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new Resource(ex.getCode(), ex.getMessage(), ex.getError()));
    }

    @ExceptionHandler(HttpFilterException.class)
    public ResponseEntity<Resource> handleUnauthorizedException(HttpFilterException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new Resource(ex.getCode(), ex.getMessage(), ex.getError()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Resource> handleMethodArgumentException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new Resource(ResponseCode.PARAMETER_VALUE_IS_INVALID, errors));
    }
}

