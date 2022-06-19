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

    public static  List<ScheduleSearchResponse> entityListToResponseList(List<Schedule> schedules) {
        List<ScheduleSearchResponse> scheduleSearchResponses = new ArrayList<>();
        for (Schedule schedule : schedules) {
            ScheduleSearchResponse scheduleSearchResponse = new ScheduleSearchResponse(
                    schedule.getId(),
                    schedule.getDate(),
                    schedule.getVisitOrder(),
                    schedule.getArrangeOrder(),
                    schedule.getVisitTime(),
                    schedule.getMemo()
            );
            Place place = schedule.getPlace();
            Flight flight = schedule.getFlight();
            if (Objects.nonNull(place)) {
                scheduleSearchResponse.setPlace(new PlaceDto(
                        place.getId(),
                        place.getName(),
                        place.getPlaceType().getKorName(),
                        place.getLocation()
                ));
            } else {
                scheduleSearchResponse.setFlight(new FlightDto(
                        flight.getId(),
                        flight.getAirline(),
                        flight.getFlightNumber(),
                        flight.getDepartureTime(),
                        flight.getArrivalTime(),
                        flight.getDepartureAirport(),
                        flight.getArrivalAirport()
                ));
            }
            scheduleSearchResponses.add(scheduleSearchResponse);
        }
        return scheduleSearchResponses;
    }

    public static Schedule saveRequestToEntity(ScheduleSaveRequest scheduleSaveRequest) {
        return new Schedule(
                scheduleSaveRequest.getDate(),
                scheduleSaveRequest.getVisitOrder(),
                scheduleSaveRequest.getArrangeOrder()
        );
    }
}
