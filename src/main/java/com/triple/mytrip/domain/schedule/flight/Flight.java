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

    /**
     * 등록, 수정 겸용 (등록되어야 할 데이터와 수정되어야 할 데이터가 같음)
     */
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
