package com.triple.mytrip.web.schedule;

import com.triple.mytrip.domain.place.Place;
import com.triple.mytrip.domain.schedule.Schedule;
import com.triple.mytrip.domain.schedule.flight.Flight;
import com.triple.mytrip.web.schedule.request.ScheduleEditRequest;
import com.triple.mytrip.web.schedule.request.ScheduleSaveRequest;
import com.triple.mytrip.web.schedule.response.ScheduleEditResponse;

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

}
