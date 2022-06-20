package com.triple.mytrip.web.schedule.response;

import com.triple.mytrip.web.place.response.PlaceSearchResponse;
import com.triple.mytrip.web.schedule.flight.response.FlightSearchResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
@NoArgsConstructor
public class ScheduleSearchResponse {

    private Long id;
    private LocalDate date;
    private Integer visitOrder;
    private Integer arrangeOrder;
    private LocalTime visitTime;
    private String memo;

    private FlightSearchResponse flight;
    private PlaceSearchResponse place;

    public ScheduleSearchResponse(Long id, LocalDate date, Integer visitOrder, Integer arrangeOrder, LocalTime visitTime, String memo) {
        this.id = id;
        this.date = date;
        this.visitOrder = visitOrder;
        this.arrangeOrder = arrangeOrder;
        this.visitTime = visitTime;
        this.memo = memo;
    }

}
