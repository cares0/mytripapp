package com.triple.mytrip.web.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class ListResult<T> {

    public int count;
    public List<T> contents;
}
