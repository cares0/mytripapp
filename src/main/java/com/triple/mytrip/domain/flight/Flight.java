package com.triple.mytrip.domain.flight;

import com.triple.mytrip.domain.common.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Flight extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "flight_id")
    private Long id;

    @Column(nullable = false)
    private String flightNumber;

    @Column(nullable = false)
    private String airline;

    @Column(nullable = false)
    private LocalDate departureDate;

    @Column(nullable = false)
    private LocalTime departureTime;

    @Column(nullable = false)
    private LocalTime arrivalTime;

    @Column(nullable = false)
    private String departureAirport;

    @Column(nullable = false)
    private String arrivalAirport;

    @Builder
    private Flight(String flightNumber, String airline, LocalDate departureDate, LocalTime departureTime, LocalTime arrivalTime, String departureAirport, String arrivalAirport) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
    }

    public void editFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void editAirline(String airline) {
        this.airline = airline;
    }

    public void editDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public void editDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public void editArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void editDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public void editArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }
}
