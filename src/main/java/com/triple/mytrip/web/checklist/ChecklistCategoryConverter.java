package com.triple.mytrip.web.checklist;

import com.triple.mytrip.domain.checklist.category.ChecklistCategory;
import com.triple.mytrip.web.checklist.response.ChecklistCategoryEditResponse;
import com.triple.mytrip.web.checklist.response.ChecklistCategorySearchResponse;
import com.triple.mytrip.web.checklist.response.ChecklistSearchResponse;
import com.triple.mytrip.web.checklist.request.ChecklistCategoryRequest;

import java.util.List;
import java.util.stream.Collectors;

public class ChecklistCategoryConverter {

    public static ChecklistCategory saveRequestToEntity(ChecklistCategoryRequest checklistCategoryRequest) {
        return new ChecklistCategory(checklistCategoryRequest.getName());
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
