package com.triple.mytrip.web.schedule.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter @Setter
@AllArgsConstructor
public class FlightDto {

    private Long id;
    private String airline;
    private String flightNumber;
    private LocalTime departureTime;
    private LocalTime arrivalTime;

    private String departureAirport;
    private String arrivalAirport;
}
