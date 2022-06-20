package com.triple.mytrip.web.schedule.response;

import com.triple.mytrip.domain.schedule.Schedule;
import com.triple.mytrip.web.place.response.PlaceSearchResponse;
import com.triple.mytrip.web.schedule.flight.response.FlightSearchResponse;
import lombok.*;

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

    private FlightSearchResponse flight;
    private PlaceSearchResponse place;

    @Builder
    public ScheduleSearchResponse(Long id, LocalDate date, Integer visitOrder, Integer arrangeOrder, LocalTime visitTime, String memo, FlightSearchResponse flight, PlaceSearchResponse place) {
        this.id = id;
        this.date = date;
        this.visitOrder = visitOrder;
        this.arrangeOrder = arrangeOrder;
        this.visitTime = visitTime;
        this.memo = memo;
        this.flight = flight;
        this.place = place;
    }

    public static ScheduleSearchResponse toResponse(Schedule schedule) {
        return ScheduleSearchResponse.builder()
                .id(schedule.getId())
                .date(schedule.getDate())
                .visitOrder(schedule.getVisitOrder())
                .arrangeOrder(schedule.getArrangeOrder())
                .visitTime(schedule.getVisitTime())
                .memo(schedule.getMemo())
                .flight(FlightSearchResponse.toResponse(schedule.getFlight()))
                .place(PlaceSearchResponse.toResponse(schedule.getPlace()))
                .build();
    }
}
