package com.triple.mytrip.web.schedule;

import com.triple.mytrip.domain.schedule.Schedule;
import com.triple.mytrip.domain.schedule.ScheduleService;
import com.triple.mytrip.web.schedule.request.ScheduleEditRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PatchMapping("/schedules/{scheduleId}")
    public Schedule edit(@PathVariable Long scheduleId, @RequestBody ScheduleEditRequest scheduleEditRequest) {
        Schedule schedule = ScheduleConverter.editRequestToEntity(scheduleEditRequest);
        Schedule modified = scheduleService.edit(scheduleId, schedule);
        return modified;
    }



}
