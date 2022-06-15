package com.triple.mytrip.web.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ErrorResult {

    private String code;
    private String message;
}
