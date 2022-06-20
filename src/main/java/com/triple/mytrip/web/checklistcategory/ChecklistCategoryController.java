package com.triple.mytrip.web.checklistcategory;

import com.triple.mytrip.domain.checklistcategory.checklist.Checklist;
import com.triple.mytrip.domain.checklistcategory.checklist.ChecklistService;
import com.triple.mytrip.domain.checklistcategory.ChecklistCategory;
import com.triple.mytrip.domain.checklistcategory.ChecklistCategoryService;
import com.triple.mytrip.web.checklistcategory.request.ChecklistCategorySaveRequest;
import com.triple.mytrip.web.checklistcategory.checklist.request.ChecklistSaveRequest;
import com.triple.mytrip.web.checklistcategory.response.ChecklistCategoryEditResponse;
import com.triple.mytrip.web.common.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.triple.mytrip.web.checklistcategory.ChecklistCategoryConverter.*;

@RestController
@RequiredArgsConstructor
public class ChecklistCategoryController {

    private final ChecklistCategoryService categoryService;
    private final ChecklistService checklistService;

    @PostMapping("/checklist-categories/{categoryId}/checklist")
    public Result<Long> saveChecklist(@PathVariable Long categoryId, @RequestBody ChecklistSaveRequest checklistSaveRequest) {
        Checklist checklist = new Checklist(checklistSaveRequest.getName());
        Long savedId = checklistService.save(categoryId, checklist);
        return new Result<>(savedId);
    }

    @PatchMapping("/checklist-categories/{categoryId}")
    public Result<ChecklistCategoryEditResponse> edit(@PathVariable Long categoryId, @RequestBody ChecklistCategorySaveRequest checklistCategorySaveRequest) {
        ChecklistCategory modifiedCategory = categoryService.editName(categoryId, checklistCategorySaveRequest.getName());
        ChecklistCategoryEditResponse checklistCategoryEditResponse = entityToEditResponse(modifiedCategory);
        return new Result<>(checklistCategoryEditResponse);
    }

    @DeleteMapping("/checklist-categories/{categoryId}")
    public Result<String> delete(@PathVariable Long categoryId) {
        categoryService.delete(categoryId);
        return new Result<>("Success");
    }
}
