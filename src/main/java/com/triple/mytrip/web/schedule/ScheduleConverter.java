package com.triple.mytrip.web.schedule;

import com.triple.mytrip.domain.schedule.Schedule;
import com.triple.mytrip.web.schedule.request.ScheduleEditRequest;

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
}
