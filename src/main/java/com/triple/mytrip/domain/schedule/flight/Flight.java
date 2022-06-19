package com.triple.mytrip.domain.schedule.flight;

import com.triple.mytrip.domain.schedule.Schedule;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

import static javax.persistence.GenerationType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Flight {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String flightNumber;
    private String airline;

    private LocalDate departureDate;

    private LocalTime departureTime;
    private LocalTime arrivalTime;

    private String departureAirport;
    private String arrivalAirport;

    public Flight(String flightNumber, String airline, LocalDate departureDate,
                  LocalTime departureTime, LocalTime arrivalTime,
                  String departureAirport, String arrivalAirport) {
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
