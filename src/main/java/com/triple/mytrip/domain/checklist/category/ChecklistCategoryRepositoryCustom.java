package com.triple.mytrip.domain.checklist.category;

import java.util.List;

public interface ChecklistCategoryRepositoryCustom {

    public List<ChecklistCategory> findAllByTripIdWithChecklist(Long tripId);
}
