package com.triple.mytrip.web.exception;

public class ValidationFailException extends RuntimeException {

    public ValidationFailException(String message) {
        super(message);
    }
}
