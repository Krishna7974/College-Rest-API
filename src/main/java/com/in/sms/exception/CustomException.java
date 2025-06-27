package com.in.sms.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomException extends RuntimeException {
    private LocalDateTime localDateTime;
    private String message;

    public CustomException(String message) {
        this.message=message;
        this.localDateTime = LocalDateTime.now();
    }
}
