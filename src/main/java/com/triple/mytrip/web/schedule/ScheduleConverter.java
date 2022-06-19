package com.triple.mytrip.web.schedule;

import com.triple.mytrip.domain.place.Place;
import com.triple.mytrip.domain.schedule.Schedule;
import com.triple.mytrip.web.schedule.request.ScheduleEditRequest;
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
            if (Objects.nonNull(place)) {
                scheduleSearchResponse.ifPlace(place.getName(), place.getPlaceType().getKorName(), place.getLocation());
            } else {
                scheduleSearchResponse.ifFlight(schedule.getFlight().getAirline(), schedule.getFlight().getFlightNumber());
            }
            scheduleSearchResponses.add(scheduleSearchResponse);
        }
        return scheduleSearchResponses;
    }
}
