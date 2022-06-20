package com.triple.mytrip.domain.trip;

import com.triple.mytrip.domain.schedule.Schedule;

import java.util.List;

public interface TripRepositoryCustom {

    Trip findAllByIdWithSchedule(Long tripId);
}
