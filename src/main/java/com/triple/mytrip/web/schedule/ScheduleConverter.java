package com.triple.mytrip.web.schedule;

import com.triple.mytrip.domain.place.Place;
import com.triple.mytrip.domain.schedule.Schedule;
import com.triple.mytrip.domain.schedule.flight.Flight;
import com.triple.mytrip.web.schedule.request.ScheduleEditRequest;
import com.triple.mytrip.web.schedule.request.ScheduleSaveRequest;
import com.triple.mytrip.web.schedule.response.FlightDto;
import com.triple.mytrip.web.schedule.response.PlaceDto;
import com.triple.mytrip.web.schedule.response.ScheduleEditResponse;
import com.triple.mytrip.web.schedule.response.ScheduleSearchResponse;
import com.triple.mytrip.web.trip.response.schdule.FlightResponse;
import com.triple.mytrip.web.trip.response.schdule.PlaceResponse;
import com.triple.mytrip.web.trip.response.schdule.ScheduleResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScheduleConverter {

    public static Schedule editRequestToEntity(ScheduleEditRequest scheduleEditRequest) {
        return new Schedule(
                scheduleEditRequest.getDate(),
                scheduleEditRequest.getVisitOrder(),
                scheduleEditRequest.getArrangeOrder(),
                scheduleEditRequest.getVisitTime(),
                scheduleEditRequest.getMemo()
        );
    }

    public static ScheduleEditResponse entityToEditResponse(Schedule modified) {
        return new ScheduleEditResponse(
                modified.getId(),
                modified.getVisitTime(),
                modified.getMemo(),
                modified.getDate(),
                modified.getVisitOrder(),
                modified.getArrangeOrder()
        );
    }

    public static Schedule saveRequestToEntity(ScheduleSaveRequest scheduleSaveRequest) {
        return new Schedule(
                scheduleSaveRequest.getDate(),
                scheduleSaveRequest.getVisitOrder(),
                scheduleSaveRequest.getArrangeOrder()
        );
    }

    public static List<ScheduleResponse> entityListToResponseList(List<Schedule> schedules) {
        List<ScheduleResponse> scheduleResponses = new ArrayList<>();
        schedules.stream().forEach((schedule) ->
                scheduleResponses.add(entityToResponse(schedule))
        );
        return scheduleResponses;
    }

    public static ScheduleResponse entityToResponse(Schedule schedule) {
        ScheduleResponse scheduleResponse = new ScheduleResponse(
                schedule.getId(),
                schedule.getDate(),
                schedule.getVisitOrder(),
                schedule.getArrangeOrder(),
                schedule.getVisitTime(),
                schedule.getMemo()
        );
        Flight flight = schedule.getFlight();
        Place place = schedule.getPlace();
        if (Objects.nonNull(flight)) {
            scheduleResponse.setFlight(new FlightResponse(
                    flight.getId(),
                    flight.getAirline(),
                    flight.getFlightNumber(),
                    flight.getDepartureTime(),
                    flight.getArrivalTime(),
                    flight.getDepartureAirport(),
                    flight.getArrivalAirport()
            ));
        } else if (Objects.nonNull(place)){
            scheduleResponse.setPlace(new PlaceResponse(
                    place.getId(),
                    place.getName(),
                    place.getPlaceType().getKorName(),
                    place.getLocation()
            ));
        } else {
            throw new RuntimeException("Mandatory 위반");
        }
        return scheduleResponse;
    }
}
