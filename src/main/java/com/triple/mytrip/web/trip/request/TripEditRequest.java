package com.triple.mytrip.web.trip.request;

import com.triple.mytrip.domain.trip.Partner;
import com.triple.mytrip.domain.trip.TripStyle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class TripEditRequest {

    private String city;
    private String title;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private Partner partner;
    private TripStyle tripStyle;

}
