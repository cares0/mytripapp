package com.triple.mytrip.web.trip.response;

import com.triple.mytrip.domain.trip.Partner;
import com.triple.mytrip.domain.trip.TripStyle;
import com.triple.mytrip.web.trip.response.schdule.ScheduleResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class TripWithScheduleResponse {

    private Long id;
    private String city;
    private String title;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private String partner;
    private String tripStyle;

    private List<ScheduleResponse> schedules;
}
