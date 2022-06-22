package com.triple.mytrip.web.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter @Setter
@AllArgsConstructor
public class ErrorResult {

    private LocalTime time;
    private String code;
    private String message;
}
