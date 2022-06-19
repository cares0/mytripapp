package com.triple.mytrip.web.schedule.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class ScheduleEditRequest {

    private LocalTime visitTime;
    private String memo;
    private LocalDate date;
    private Integer visitOrder;
    private Integer arrangeOrder;

}
