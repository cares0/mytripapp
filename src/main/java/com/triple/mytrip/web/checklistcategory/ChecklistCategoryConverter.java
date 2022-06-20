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
        return new ChecklistCategory(checklistCategorySaveRequest.getName());
    }

    public static ChecklistCategoryEditResponse entityToEditResponse(ChecklistCategory modifiedCategory) {
        return new ChecklistCategoryEditResponse(modifiedCategory.getId(), modifiedCategory.getName());
    }

    public static List<ChecklistCategorySearchResponse> entityListToDtoList(List<ChecklistCategory> result) {
        return result.stream().map((category) -> {
            List<ChecklistSearchResponse> checklistSearchResponses = category.getChecklists().stream().map((checklist) -> new ChecklistSearchResponse(
                    checklist.getId(),
                    checklist.getCheckStatus(),
                    checklist.getBasicOfferStatus(),
                    checklist.getName(),
                    checklist.getMemo(),
                    checklist.getInstruction())).collect(Collectors.toList());
            return new ChecklistCategorySearchResponse(
                    category.getId(),
                    category.getTrip().getId(),
                    checklistSearchResponses,
                    category.getBasicOfferStatus(),
                    category.getName()
            );
        }).collect(Collectors.toList());
    }
}
