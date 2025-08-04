package com.Shop.Sizzle.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Map<String, String>> methodArgumentNotValidException
            (MethodArgumentNotValidException ve) {
        Map<String, String> response = new HashMap<>();
        ve.getBindingResult().getAllErrors().forEach(err -> {
            String fieldName = ((FieldError) err).getField();
            String message = err.getDefaultMessage();
            response.put(fieldName, message);
        });
        return new ResponseEntity<Map<String,String>>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<String> resourceNotFoundException(ResourceNotFoundException re) {
        String message = re.getMessage();
        return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(APIException.class)
    ResponseEntity<String> aPIException(APIException e) {
        String message = e.getMessage();
        return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
    }
}
