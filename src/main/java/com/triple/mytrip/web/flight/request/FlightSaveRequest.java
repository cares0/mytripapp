package com.triple.mytrip.web.flight.request;

import com.triple.mytrip.domain.flight.Flight;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

import static lombok.AccessLevel.*;

@Getter @Setter
@NoArgsConstructor(access = PRIVATE)
public class FlightSaveRequest {

    @NotBlank
    private String flightNumber;

    @NotBlank
    private String airline;

    @NotNull
    private LocalDate departureDate;

    @NotNull
    private LocalTime departureTime;

    @NotNull
    private LocalTime arrivalTime;

    @NotBlank
    private String departureAirport;

    @NotBlank
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
