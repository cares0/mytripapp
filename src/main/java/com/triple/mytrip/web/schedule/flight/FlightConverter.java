package com.triple.mytrip.web.schedule.flight;

import com.triple.mytrip.domain.schedule.flight.Flight;
import com.triple.mytrip.web.schedule.flight.request.FlightSaveRequest;

public class FlightConverter {


    public static Flight saveRequestToEntity(FlightSaveRequest flightSaveRequest) {
        return new Flight(
                flightSaveRequest.getFlightNumber(),
                flightSaveRequest.getAirline(),
                flightSaveRequest.getDepartureDate(),
                flightSaveRequest.getDepartureTime(),
                flightSaveRequest.getArrivalTime(),
                flightSaveRequest.getDepartureAirport(),
                flightSaveRequest.getArrivalAirport()
        );
    }
}
