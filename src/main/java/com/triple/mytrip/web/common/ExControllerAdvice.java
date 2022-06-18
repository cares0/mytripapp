package com.triple.mytrip.web.common;

import com.triple.mytrip.domain.exception.EntityNotFoundException;
import com.triple.mytrip.web.exception.NoModifiedDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalTime;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ErrorResult entityNotFoundExHandler(EntityNotFoundException e) {
        log.error("[EntityNotFoundException]", e);

        return new ErrorResult(LocalTime.now(), "EntityNotFound", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult HttpMessageNotReadableExHandler(HttpMessageNotReadableException e) {
        log.error("[HttpMessageNotReadableException]", e);
        return new ErrorResult(LocalTime.now(), "WrongMessage", e.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult NoModifiedDataExHandler(NoModifiedDataException e) {
        log.error("[NoModifiedDataException]", e);
        return new ErrorResult(LocalTime.now(), "NoData", e.getMessage());
    }

}
