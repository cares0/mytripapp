package com.triple.mytrip.web.flight.request;

import com.triple.mytrip.domain.flight.Flight;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

import static lombok.AccessLevel.*;

@Getter @Setter
@NoArgsConstructor(access = PRIVATE)
public class FlightEditRequest {

    private String flightNumber;
    private String airline;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private String departureAirport;
    private String arrivalAirport;

    public Flight toEntity() {
        return Flight.builder()
                .flightNumber(this.flightNumber)
                .airline(this.airline)
                .departureDate(this.departureDate)
                .departureTime(this.departureTime)
                .arrivalTime(this.arrivalTime)
                .departureAirport(this.departureAirport)
                .arrivalAirport(this.arrivalAirport)
                .build();
    }

}