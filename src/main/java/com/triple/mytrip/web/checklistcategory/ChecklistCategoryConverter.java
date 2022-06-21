package com.triple.mytrip.web.checklistcategory;

import com.triple.mytrip.domain.checklistcategory.ChecklistCategory;
import com.triple.mytrip.web.checklistcategory.response.ChecklistCategoryEditResponse;
import com.triple.mytrip.web.checklistcategory.response.ChecklistCategorySearchResponse;
import com.triple.mytrip.web.checklistcategory.checklist.response.ChecklistSearchResponse;
import com.triple.mytrip.web.checklistcategory.request.ChecklistCategorySaveRequest;

import java.util.List;
import java.util.stream.Collectors;

public class ChecklistCategoryConverter {

    public static ChecklistCategory saveRequestToEntity(ChecklistCategorySaveRequest checklistCategorySaveRequest) {
        return null;
    }

    public static ChecklistCategoryEditResponse entityToEditResponse(ChecklistCategory modifiedCategory) {
        return null;
    }

    public static List<ChecklistCategorySearchResponse> entityListToDtoList(List<ChecklistCategory> result) {
        return null;
    }
}
