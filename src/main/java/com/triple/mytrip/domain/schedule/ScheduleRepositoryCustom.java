package com.triple.mytrip.domain.schedule;

import java.util.List;

public interface ScheduleRepositoryCustom {

    List<Schedule> findAllByTripIdWithAll(Long tripId);
}
