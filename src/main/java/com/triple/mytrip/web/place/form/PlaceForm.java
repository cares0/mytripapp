package com.triple.mytrip.web.place.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
@ToString
public class PlaceForm {

    private String name;
    private LocalDate date;
    private String location;
    private Integer placeOrder;

    // Flight
    private String flightNumber;
    private String airline;

    private LocalDate departureDate;

    private LocalTime departureTime;
    private LocalTime arrivalTime;

    private String departureAirport;
    private String arrivalAirport;

}

