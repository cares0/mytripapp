package com.triple.mytrip.domain.exception;

public class NotInitPeriodException extends RuntimeException {

    public NotInitPeriodException() {
        super("여행 기간이 초기화되지 않음, 여행 기간을 우선 초기화 하세요.");
    }
}
