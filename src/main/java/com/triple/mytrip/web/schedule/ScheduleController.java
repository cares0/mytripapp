package com.triple.mytrip.web.schedule;

import com.triple.mytrip.domain.schedule.Schedule;
import com.triple.mytrip.domain.schedule.ScheduleService;
import com.triple.mytrip.web.common.Result;
import com.triple.mytrip.web.schedule.request.ScheduleEditRequest;
import com.triple.mytrip.web.schedule.response.ScheduleEditResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PatchMapping("/schedules/{scheduleId}")
    public ScheduleEditResponse edit(@PathVariable Long scheduleId, @RequestBody ScheduleEditRequest scheduleEditRequest) {
        Schedule schedule = ScheduleConverter.editRequestToEntity(scheduleEditRequest);
        Schedule modified = scheduleService.edit(scheduleId, schedule);
        ScheduleEditResponse result = ScheduleConverter.entityToEditResponse(modified);
        return result;
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public Result<String> delete(@PathVariable Long scheduleId) {
        scheduleService.delete(scheduleId);
        return new Result<>("success");
    }

}
