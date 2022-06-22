package com.triple.mytrip.web.checklistcategory;

import com.triple.mytrip.domain.checklistcategory.checklist.Checklist;
import com.triple.mytrip.domain.checklistcategory.ChecklistCategory;
import com.triple.mytrip.domain.checklistcategory.ChecklistCategoryService;
import com.triple.mytrip.web.checklistcategory.request.ChecklistCategoryEditRequest;
import com.triple.mytrip.web.checklistcategory.checklist.request.ChecklistSaveRequest;
import com.triple.mytrip.web.checklistcategory.response.ChecklistCategoryEditResponse;
import com.triple.mytrip.web.common.Result;
import com.triple.mytrip.web.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class ChecklistCategoryController {

    private final ChecklistCategoryService categoryService;
    private final ValidationUtils validationUtils;

    @PostMapping("/checklist-categories/{categoryId}/checklists")
    public Result<Long> checklistAdd(@PathVariable Long categoryId, @Validated @RequestBody ChecklistSaveRequest checklistSaveRequest, BindingResult bindingResult) {
        validationUtils.validate(bindingResult);

        Checklist checklist = checklistSaveRequest.toEntity();
        Long savedId = categoryService.addChecklist(categoryId, checklist);
        return new Result<>(savedId);
    }

    @PatchMapping("/checklist-categories/{categoryId}")
    public ChecklistCategoryEditResponse checklistCategoryModify(@PathVariable Long categoryId, @RequestBody ChecklistCategoryEditRequest checklistCategoryEditRequest) {
        ChecklistCategory modified = checklistCategoryEditRequest.toEntity();
        modified = categoryService.modify(categoryId, modified);
        return ChecklistCategoryEditResponse.toResponse(modified);
    }

    @DeleteMapping("/checklist-categories/{categoryId}")
    public Result<Long> checklistCategoryRemove(@PathVariable Long categoryId) {
        categoryService.remove(categoryId);
        return new Result<>(categoryId);
    }
}
