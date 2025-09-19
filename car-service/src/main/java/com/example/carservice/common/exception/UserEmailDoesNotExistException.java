package com.example.carservice.common.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserEmailDoesNotExistException extends RuntimeException {
    public UserEmailDoesNotExistException(String message) {
        super(message);
    }
}
