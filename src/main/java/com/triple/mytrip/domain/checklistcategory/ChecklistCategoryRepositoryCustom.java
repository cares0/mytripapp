package com.triple.mytrip.domain.checklistcategory;

import java.util.List;

public interface ChecklistCategoryRepositoryCustom {

    public List<ChecklistCategory> findAllByTripIdWithChecklist(Long tripId);
}
