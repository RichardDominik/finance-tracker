package com.financetracker.server.data.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class CategoryException extends RuntimeException {

    public CategoryException() {
        super();
    }

    public CategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryException(String message) {
        super(message);
    }

    public CategoryException(Throwable cause) {
        super(cause);
    }
}