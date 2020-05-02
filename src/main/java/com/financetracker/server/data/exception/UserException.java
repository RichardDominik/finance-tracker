package com.financetracker.server.data.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserException extends RuntimeException {

    public UserException() {
        super();
    }
    public UserException(String message, Throwable cause) {
        super(message, cause);
    }
    public UserException(String message) {
        super(message);
    }
    public UserException(Throwable cause) {
        super(cause);
    }
}