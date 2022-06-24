package com.triple.mytrip.domain.exception;

import java.time.LocalDate;

public class EntityNotWithinPeriodException extends RuntimeException {

    public EntityNotWithinPeriodException(LocalDate date, LocalDate start, LocalDate end) {
        super(date + "는 " + start + "와 " + end + "사이에 속하지 않습니다.");
    }
}
