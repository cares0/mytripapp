package com.triple.mytrip.web.checklist;

import com.triple.mytrip.domain.checklist.category.ChecklistCategory;
import com.triple.mytrip.web.checklist.response.ChecklistCategoryDto;
import com.triple.mytrip.web.checklist.response.ChecklistSearchResponse;
import com.triple.mytrip.web.checklist.request.ChecklistCategorySaveRequest;

import java.util.List;
import java.util.stream.Collectors;

public class ChecklistCategoryConverter {

    public static ChecklistCategory formToEntity(ChecklistCategorySaveRequest categoryForm) {
        return new ChecklistCategory(categoryForm.getName());
    }

    public static List<ChecklistCategoryDto> entityListToDtoList(List<ChecklistCategory> result) {
        return result.stream().map((category) -> {
            List<ChecklistSearchResponse> checklistSearchResponses = category.getChecklists().stream().map((checklist) -> new ChecklistSearchResponse(
                    checklist.getId(),
                    checklist.getCheckStatus(),
                    checklist.getBasicOfferStatus(),
                    checklist.getName(),
                    checklist.getMemo(),
                    checklist.getInstruction())).collect(Collectors.toList());
            return new ChecklistCategoryDto(
                    category.getId(),
                    category.getTrip().getId(),
                    checklistSearchResponses,
                    category.getBasicOfferStatus(),
                    category.getName()
            );
        }).collect(Collectors.toList());
    }
}
