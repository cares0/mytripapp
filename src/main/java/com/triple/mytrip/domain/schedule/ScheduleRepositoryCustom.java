package com.triple.mytrip.domain.schedule;

import java.util.Optional;

public interface ScheduleRepositoryCustom {

    Optional<Schedule> findByIdWithAll(Long scheduleId);

}
