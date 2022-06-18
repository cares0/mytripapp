package com.triple.mytrip.web.exception;

public class NoModifiedDataException extends IllegalArgumentException {

    public NoModifiedDataException() {
        super("수정하려는 데이터가 없음");
    }
}
