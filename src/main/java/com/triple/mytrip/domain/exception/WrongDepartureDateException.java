package com.triple.mytrip.domain.exception;

public class WrongDepartureDateException extends RuntimeException {
    public WrongDepartureDateException() {
        super("출발일이 도착일보다 앞섭니다.");
    }
}
