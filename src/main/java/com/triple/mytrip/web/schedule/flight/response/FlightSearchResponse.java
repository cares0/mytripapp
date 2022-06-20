package com.triple.mytrip.web.schedule.flight.response;

import com.triple.mytrip.domain.schedule.flight.Flight;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Getter @Setter
public class FlightSearchResponse {

    private Long id;
    private String flightNumber;
    private String airline;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private String departureAirport;
    private String arrivalAirport;

    @Builder
    public FlightSearchResponse(Long id, String flightNumber, String airline, LocalDate departureDate, LocalTime departureTime, LocalTime arrivalTime, String departureAirport, String arrivalAirport) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
    }

    public static FlightSearchResponse toResponse(Flight flight) {
        if (Objects.isNull(flight)) {
            return null;
        }
        return FlightSearchResponse.builder()
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
