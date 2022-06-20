package com.triple.mytrip.web.trip;

import com.triple.mytrip.domain.schedule.Schedule;
import com.triple.mytrip.domain.trip.Trip;
import com.triple.mytrip.web.schedule.ScheduleConverter;
import com.triple.mytrip.web.trip.request.TripEditRequest;
import com.triple.mytrip.web.trip.response.TripEditResponse;
import com.triple.mytrip.web.trip.response.TripWithScheduleResponse;
import com.triple.mytrip.web.trip.response.schdule.ScheduleResponse;

import java.util.List;

public class TripConverter {

    public static Trip editRequestToEntity(TripEditRequest tripEditRequest) {
        return new Trip(
                tripEditRequest.getCity(),
                tripEditRequest.getTitle(),
                tripEditRequest.getArrivalDate(),
                tripEditRequest.getDepartureDate(),
                tripEditRequest.getPartner(),
                tripEditRequest.getTripStyle()
        );
    }

    public static TripEditResponse entityToEditResponse(Trip modified) {
        return new TripEditResponse(
                modified.getId(),
                modified.getCity(),
                modified.getTitle(),
                modified.getArrivalDate(),
                modified.getDepartureDate(),
                modified.getPartner().getKorName(),
                modified.getTripStyle().getKorName()
        );
    }

    public static TripWithScheduleResponse entityToResponse(Trip trip) {
        List<Schedule> schedules = trip.getSchedules();
        List<ScheduleResponse> scheduleResponses = ScheduleConverter.entityListToResponseList(schedules);
        return new TripWithScheduleResponse(
                trip.getId(),
                trip.getCity(),
                trip.getTitle(),
                trip.getArrivalDate(),
                trip.getDepartureDate(),
                trip.getPartner().getKorName(),
                trip.getTripStyle().getKorName(),
                scheduleResponses
        );
    }
}
