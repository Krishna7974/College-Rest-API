package com.in.sms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<CustomException> handleGenericException(RuntimeException ex) {
        CustomException customException=new CustomException(ex.getMessage());
        return new ResponseEntity<>(
                customException,
                HttpStatus.BAD_REQUEST
        );
    }
}