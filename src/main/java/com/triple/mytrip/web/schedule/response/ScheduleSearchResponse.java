package com.triple.mytrip.web.schedule.response;

import com.triple.mytrip.domain.schedule.Schedule;
import com.triple.mytrip.web.flight.response.FlightSearchResponse;
import com.triple.mytrip.web.place.response.PlaceSearchResponse;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import static lombok.AccessLevel.*;

@Getter @Setter
@Builder
@AllArgsConstructor(access = PRIVATE)
public class ScheduleSearchResponse {

    private Long id;
    private LocalDate date;
    private Integer visitOrder;
    private Integer arrangeOrder;
    private LocalTime visitTime;
    private String memo;

    private PlaceSearchResponse place;
    private FlightSearchResponse flight;

    public static ScheduleSearchResponse toResponse(Schedule schedule) {
        PlaceSearchResponse placeSearchResponse = Objects.isNull(schedule.getPlace()) ?
                null : PlaceSearchResponse.toResponse(schedule.getPlace());

        FlightSearchResponse flightSearchResponse = Objects.isNull(schedule.getFlight()) ?
                null : FlightSearchResponse.toResponse(schedule.getFlight());

        return ScheduleSearchResponse.builder()
                .id(schedule.getId())
                .date(schedule.getDate())
                .visitOrder(schedule.getVisitOrder())
                .arrangeOrder(schedule.getArrangeOrder())
                .visitTime(schedule.getVisitTime())
                .memo(schedule.getMemo())
                .place(placeSearchResponse)
                .flight(flightSearchResponse)
                .build();
    }

}
