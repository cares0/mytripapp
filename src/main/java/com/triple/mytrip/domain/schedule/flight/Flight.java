package com.triple.mytrip.domain.schedule.flight;

import com.triple.mytrip.domain.schedule.Schedule;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@DiscriminatorValue("FLIGHT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Flight extends Schedule {

    private String flightNumber;
    private String airline;

    private LocalDate departureDate;

    private LocalTime departureTime;
    private LocalTime arrivalTime;

    private String departureAirport;
    private String arrivalAirport;

    public Flight(LocalDate date, String location, Integer placeOrder,
                  String flightNumber, String airline, LocalDate departureDate,
                  LocalTime departureTime, LocalTime arrivalTime,
                  String departureAirport, String arrivalAirport) {
        super(createName(departureTime, arrivalTime, departureAirport, arrivalAirport), date, location, placeOrder);
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
    }

    private static String createName(LocalTime departureTime, LocalTime arrivalTime, String departureAirport, String arrivalAirport) {
        return departureAirport + departureTime + " - " + arrivalAirport + arrivalTime;
    }

}
