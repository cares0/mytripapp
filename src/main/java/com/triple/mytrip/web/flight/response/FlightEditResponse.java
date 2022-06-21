package com.triple.mytrip.web.flight.response;

import com.triple.mytrip.domain.flight.Flight;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

import static lombok.AccessLevel.*;

@Getter @Setter
@Builder
@AllArgsConstructor(access = PRIVATE)
public class FlightEditResponse {

    private Long id;
    private String flightNumber;
    private String airline;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private String departureAirport;
    private String arrivalAirport;

    public static FlightEditResponse toResponse(Flight flight) {
        return FlightEditResponse.builder()
                .id(flight.getId())
                .flightNumber(flight.getFlightNumber())
                .airline(flight.getAirline())
                .departureDate(flight.getDepartureDate())
                .departureTime(flight.getDepartureTime())
                .arrivalTime(flight.getArrivalTime())
                .departureAirport(flight.getDepartureAirport())
                .arrivalAirport(flight.getArrivalAirport())
                .build();
    }
}
