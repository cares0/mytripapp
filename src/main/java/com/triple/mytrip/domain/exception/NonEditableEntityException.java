package com.triple.mytrip.domain.exception;

public class NonEditableEntityException extends RuntimeException {

    public NonEditableEntityException(String message) {
        super(message);
    }
}
