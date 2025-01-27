package com.example.newsproject.common.exception;

import org.springframework.http.HttpStatus;


public class newValidationException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;

    public newValidationException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus=httpStatus;
    }

    public String getMessage(){
        return message;
    }

    public HttpStatus getHttpStatus(){
        return httpStatus;
    }
}
