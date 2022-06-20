package com.triple.mytrip.web.trip.response.schdule;

import com.triple.mytrip.web.schedule.response.FlightDto;
import com.triple.mytrip.web.schedule.response.PlaceDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
public class ScheduleResponse {

    private Long id;
    private LocalDate date;
    private Integer visitOrder;
    private Integer arrangeOrder;
    private LocalTime visitTime;
    private String memo;
    private PlaceResponse place;
    private FlightResponse flight;

    public ScheduleResponse(Long id, LocalDate date, Integer visitOrder, Integer arrangeOrder, LocalTime visitTime, String memo) {
        this.id = id;
        this.date = date;
        this.visitOrder = visitOrder;
        this.arrangeOrder = arrangeOrder;
        this.visitTime = visitTime;
        this.memo = memo;
    }
}
