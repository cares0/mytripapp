package com.triple.mytrip.domain.checklist.category;

import com.triple.mytrip.domain.trip.Trip;

import java.util.List;

public interface ChecklistCategoryRepositoryCustom {

    public List<ChecklistCategory> findAllByTripWithChecklist(Long tripId);
}
