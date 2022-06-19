package com.triple.mytrip.web.schedule.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class ScheduleSaveRequest {

    private LocalDate date;
    private Integer visitOrder;
    private Integer arrangeOrder;
}
