package com.triple.mytrip.web.schedule.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
public class ScheduleSearchResponse {

    private Long id;
    private LocalDate date;
    private Integer visitOrder;
    private Integer arrangeOrder;
    private LocalTime visitTime;
    private String memo;

    // place
    private String name;
    private String placeType;
    private String location;
    // flight

    private String airline;
    private String flightNumber;

    public ScheduleSearchResponse(Long id, LocalDate date, Integer visitOrder, Integer arrangeOrder, LocalTime visitTime, String memo) {
        this.id = id;
        this.date = date;
        this.visitOrder = visitOrder;
        this.arrangeOrder = arrangeOrder;
        this.visitTime = visitTime;
        this.memo = memo;
    }

    public void ifPlace(String name, String placeType, String location) {
        this.name = name;
        this.placeType = placeType;
        this.location = location;
    }

    public void ifFlight(String airline, String flightNumber) {
        this.airline = airline;
        this.flightNumber = flightNumber;
    }
}
