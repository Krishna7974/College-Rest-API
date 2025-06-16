package com.in.sms.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomException> handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> messageList = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList();
        logger.error("Exception caught in Constraint Validation: {}",String.join(", ", messageList));
        CustomException customException = new CustomException(String.join(", ", messageList));
        return new ResponseEntity<>(
                customException,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomException> handleGenericException(RuntimeException ex) {
        logger.error("Exception caught in global handler: {}", ex.getMessage());
        CustomException customException = new CustomException(ex.getMessage());
        return new ResponseEntity<>(
                customException,
                HttpStatus.BAD_REQUEST
        );
    }

}