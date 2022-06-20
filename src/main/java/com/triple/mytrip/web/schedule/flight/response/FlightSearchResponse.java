package com.triple.mytrip.web.schedule.flight.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
@AllArgsConstructor
public class FlightSearchResponse {

    private Long id;
    private String flightNumber;
    private String airline;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private String departureAirport;
    private String arrivalAirport;
}