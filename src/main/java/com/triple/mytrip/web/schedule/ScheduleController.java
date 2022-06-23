package com.triple.mytrip.web.schedule;

import com.triple.mytrip.domain.schedule.Schedule;
import com.triple.mytrip.domain.schedule.ScheduleService;
import com.triple.mytrip.web.common.Result;
import com.triple.mytrip.web.schedule.request.ScheduleEditRequest;
import com.triple.mytrip.web.schedule.response.ScheduleEditResponse;
import com.triple.mytrip.web.schedule.response.ScheduleSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/schedules/{scheduleId}")
    public ScheduleSearchResponse scheduleDetailsWithAll(@PathVariable Long scheduleId) {
        Schedule schedule = scheduleService.getOneWithAll(scheduleId);
        return ScheduleSearchResponse.toResponse(schedule);
    }

    @PatchMapping("/schedules/{scheduleId}")
    public ScheduleEditResponse scheduleModify(@PathVariable Long scheduleId,
                                               @RequestBody ScheduleEditRequest scheduleEditRequest) {
        Schedule modified = scheduleEditRequest.toEntity();
        modified = scheduleService.modify(scheduleId, modified);
        return ScheduleEditResponse.toResponse(modified);
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public Result<Long> scheduleRemove(@PathVariable Long scheduleId) {
        scheduleService.remove(scheduleId);
        return new Result<>(scheduleId);
    }

}
