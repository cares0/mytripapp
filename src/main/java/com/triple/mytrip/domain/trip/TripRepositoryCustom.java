package com.triple.mytrip.domain.trip;

import java.util.Optional;

public interface TripRepositoryCustom {

    Optional<Trip> findByIdWithSchedule(Long tripId);

    Optional<Trip> findByIdWithChecklistCategory(Long tripId);

    Optional<Trip> findByIdWithBudget(Long tripId);
}
