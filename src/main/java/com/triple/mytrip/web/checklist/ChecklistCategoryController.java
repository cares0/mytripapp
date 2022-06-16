package com.triple.mytrip.web.checklist;

import com.triple.mytrip.domain.checklist.Checklist;
import com.triple.mytrip.domain.checklist.ChecklistService;
import com.triple.mytrip.domain.checklist.category.ChecklistCategoryService;
import com.triple.mytrip.web.checklist.form.ChecklistForm;
import com.triple.mytrip.web.common.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChecklistCategoryController {

    private final ChecklistCategoryService categoryService;
    private final ChecklistService checklistService;

    @PostMapping("/checklist-categories/{categoryId}/checklist")
    public Result<Long> saveChecklist(@PathVariable Long categoryId, @RequestBody ChecklistForm checklistForm) {
        Checklist checklist = new Checklist(checklistForm.getName());
        Long savedId = checklistService.save(categoryId, checklist);
        return new Result<>(savedId);
    }

    @PatchMapping("/checklist-categories/{categoryId}")
    public Result<String> editName(@PathVariable Long categoryId, @RequestBody String name) {
        categoryService.editName(categoryId, name);
        return new Result<>("Success");
    }

    @DeleteMapping("/checklist-categories/{categoryId}")
    public Result<String> delete(@PathVariable Long categoryId) {
        categoryService.delete(categoryId);
        return new Result<>("Success");
    }
}
