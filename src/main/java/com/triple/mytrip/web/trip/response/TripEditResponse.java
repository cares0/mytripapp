package com.triple.mytrip.web.trip.response;

import com.triple.mytrip.domain.trip.Partner;
import com.triple.mytrip.domain.trip.TripStyle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
public class TripEditResponse {

    private Long id;
    private String city;
    private String title;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private String partner;
    private String tripStyle;
}