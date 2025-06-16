package com.in.sms.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomException> handleGenericException(RuntimeException ex) {
        logger.error("Exception caught in global handler: {}",ex.getMessage());
        CustomException customException=new CustomException(ex.getMessage());
        return new ResponseEntity<>(
                customException,
                HttpStatus.BAD_REQUEST
        );
    }
}