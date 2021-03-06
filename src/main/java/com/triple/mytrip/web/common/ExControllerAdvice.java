package com.triple.mytrip.web.common;

import com.triple.mytrip.domain.exception.EntityNotFoundException;
import com.triple.mytrip.domain.exception.EntityNotWithinPeriodException;
import com.triple.mytrip.domain.exception.NotInitPeriodException;
import com.triple.mytrip.domain.exception.WrongDepartureDateException;
import com.triple.mytrip.web.exception.ValidationFailException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public ErrorResult entityNotWithinPeriodExHandler(EntityNotWithinPeriodException e) {
        log.error("[EntityNotWithinPeriodException]", e);
        return new ErrorResult(LocalTime.now(), "NotWithinTripDate", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult validationFailExHandler(ValidationFailException e) {
        log.error("[ValidationFailException]", e);
        return new ErrorResult(LocalTime.now(), "ValidationFail", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult httpMessageNotReadableExHandler(HttpMessageNotReadableException e) {
        log.error("[HttpMessageNotReadableException]", e);
        return new ErrorResult(LocalTime.now(), "WrongMessage", e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ErrorResult emptyResultDataAccessExHandler(EmptyResultDataAccessException e) {
        log.error("[EmptyResultDataAccessException]", e);
        return new ErrorResult(LocalTime.now(), "EntityNotFound", "??????????????? ???????????? ????????????.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult constraintViolationExHandler(ConstraintViolationException e) {
        log.error("[ConstraintViolationException]", e);
        return new ErrorResult(LocalTime.now(), "ConstraintViolation", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult wrongDepartureDateExHandler(WrongDepartureDateException e) {
        log.error("[WrongDepartureDateException]", e);
        return new ErrorResult(LocalTime.now(), "WrongDate", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult notInitPeriodExHandler(NotInitPeriodException e) {
        log.error("[NotInitPeriodException]", e);
        return new ErrorResult(LocalTime.now(), "NotInitPeriod", e.getMessage());
    }

}
