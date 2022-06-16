package com.triple.mytrip.web.checklist;

import com.triple.mytrip.domain.checklist.category.ChecklistCategoryService;
import com.triple.mytrip.web.common.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChecklistCategoryController {

    private final ChecklistCategoryService checklistCategoryService;

    @PatchMapping("/checklist-categories/{categoryId}")
    public Result<String> editName(@PathVariable Long categoryId, @RequestBody String name) {
        checklistCategoryService.editName(categoryId, name);
        return new Result<>("Success");
    }
}
