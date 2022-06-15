package com.triple.mytrip.domain.flight;

import com.triple.mytrip.domain.trip.Trip;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Flight {

    @Id @GeneratedValue
    @Column(name = "place_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip;

    private String flightNumber;

    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;

    private String departureAirport;
    private String arrivalAirport;

    public Flight(Trip trip, String flightNumber, LocalDate departureDate, LocalTime departureTime,
                  LocalTime arrivalTime, String departureAirport, String arrivalAirport) {
        addTrip(trip);
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
    }

    private void addTrip(Trip trip) {
        this.trip = trip;
    }

    public void editAll(String flightNumber, LocalDate departureDate, LocalTime departureTime,
                        LocalTime arrivalTime, String departureAirport, String arrivalAirport) {
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
    }
}
