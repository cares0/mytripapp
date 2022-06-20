package com.triple.mytrip.web.trip.response;

import com.triple.mytrip.web.schedule.response.ScheduleSearchResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class TripSearchResponse {

    private Long id;
    private String city;
    private String title;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private String partner;
    private String tripStyle;

    private List<ScheduleSearchResponse> schedules;
}
