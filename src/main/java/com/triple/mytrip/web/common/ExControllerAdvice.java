package com.triple.mytrip.web.common;

import com.triple.mytrip.domain.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult entityNotFoundExHandler(EntityNotFoundException e) {
        log.error("[EntityNotFoundException]", e);
        return new ErrorResult("EntityNotFound", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult HttpMessageNotReadableExHandler(HttpMessageNotReadableException e) {
        log.error("[HttpMessageNotReadableException]", e);
        return new ErrorResult("WrongMessage", e.getMessage());
    }

}
