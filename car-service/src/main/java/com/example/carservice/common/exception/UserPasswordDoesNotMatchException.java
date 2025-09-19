package com.example.carservice.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserPasswordDoesNotMatchException extends RuntimeException {
    public UserPasswordDoesNotMatchException(String message) {
        super(message);
    }
}
