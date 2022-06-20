package com.triple.mytrip.web.flight;

import com.triple.mytrip.domain.flight.Flight;
import com.triple.mytrip.web.flight.request.FlightEditRequest;
import com.triple.mytrip.web.flight.request.FlightSaveRequest;
import com.triple.mytrip.web.flight.response.FlightEditResponse;
import com.triple.mytrip.web.flight.response.FlightSearchResponse;

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

    public static FlightSearchResponse entityToSearchResponse(Flight flight) {
        FlightSearchResponse flightSearchResponse = new FlightSearchResponse(
                flight.getId(),
                flight.getFlightNumber(),
                flight.getAirline(),
                flight.getDepartureDate(),
                flight.getDepartureTime(),
                flight.getArrivalTime(),
                flight.getDepartureAirport(),
                flight.getArrivalAirport()
        );
        return flightSearchResponse;
    }

    public static Flight editRequestToEntity(FlightEditRequest flightEditRequest) {
        Flight flight = new Flight(
                flightEditRequest.getFlightNumber(),
                flightEditRequest.getAirline(),
                flightEditRequest.getDepartureDate(),
                flightEditRequest.getDepartureTime(),
                flightEditRequest.getArrivalTime(),
                flightEditRequest.getDepartureAirport(),
                flightEditRequest.getArrivalAirport()
        );
        return flight;
    }

    public static FlightEditResponse entityToEditResponse(Flight modified) {
        return new FlightEditResponse(
                modified.getId(),
                modified.getFlightNumber(),
                modified.getAirline(),
                modified.getDepartureDate(),
                modified.getDepartureTime(),
                modified.getArrivalTime(),
                modified.getDepartureAirport(),
                modified.getArrivalAirport()
        );
    }
}
