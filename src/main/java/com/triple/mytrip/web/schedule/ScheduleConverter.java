package com.triple.mytrip.web.schedule;

import com.triple.mytrip.domain.schedule.Schedule;
import com.triple.mytrip.web.schedule.request.ScheduleEditRequest;
import com.triple.mytrip.web.schedule.response.ScheduleEditResponse;

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
}
