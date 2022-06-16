package com.triple.mytrip.web.checklist;

import com.triple.mytrip.domain.checklist.category.ChecklistCategory;
import com.triple.mytrip.web.checklist.dto.ChecklistCategoryDto;
import com.triple.mytrip.web.checklist.dto.ChecklistDto;
import com.triple.mytrip.web.checklist.form.ChecklistCategoryForm;

import java.util.List;
import java.util.stream.Collectors;

public class ChecklistCategoryConverter {

    public static ChecklistCategory formToEntity(ChecklistCategoryForm categoryForm) {
        return new ChecklistCategory(categoryForm.getName());
    }

    public static List<ChecklistCategoryDto> entityListToDtoList(List<ChecklistCategory> result) {
        return result.stream().map((category) -> {
            List<ChecklistDto> checklistDtos = category.getChecklists().stream().map((checklist) -> new ChecklistDto(
                    checklist.getId(),
                    checklist.getCheckStatus(),
                    checklist.getBasicOfferStatus(),
                    checklist.getName(),
                    checklist.getMemo(),
                    checklist.getInstruction())).collect(Collectors.toList());
            return new ChecklistCategoryDto(
                    category.getId(),
                    category.getTrip().getId(),
                    checklistDtos,
                    category.getBasicOfferStatus(),
                    category.getName()
            );
        }).collect(Collectors.toList());
    }
}
