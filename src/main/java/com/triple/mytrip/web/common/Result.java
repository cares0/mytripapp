package com.triple.mytrip.web.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
public class Result<T> {

    private T result;
}
