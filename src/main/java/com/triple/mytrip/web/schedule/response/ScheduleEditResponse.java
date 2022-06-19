package com.triple.mytrip.web.schedule.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
@AllArgsConstructor
public class ScheduleEditResponse {

    private Long id;
    private LocalTime visitTime;
    private String memo;
    private LocalDate date;
    private Integer visitOrder;
    private Integer arrangeOrder;

}
