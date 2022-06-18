package com.triple.mytrip.web.trip;

import com.triple.mytrip.domain.budget.Budget;
import com.triple.mytrip.domain.budget.BudgetService;
import com.triple.mytrip.domain.checklist.category.ChecklistCategory;
import com.triple.mytrip.domain.checklist.category.ChecklistCategoryService;
import com.triple.mytrip.domain.place.Place;
import com.triple.mytrip.domain.place.PlaceService;
import com.triple.mytrip.web.budget.BudgetConverter;
import com.triple.mytrip.web.budget.response.BudgetSearchResponse;
import com.triple.mytrip.web.budget.request.BudgetSaveRequest;
import com.triple.mytrip.web.checklist.ChecklistCategoryConverter;
import com.triple.mytrip.web.checklist.response.ChecklistCategoryDto;
import com.triple.mytrip.web.checklist.request.ChecklistCategorySaveRequest;
import com.triple.mytrip.web.common.ListResult;
import com.triple.mytrip.web.common.Result;
import com.triple.mytrip.web.place.PlaceConverter;
import com.triple.mytrip.web.place.form.PlaceForm;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TripController {

    private final BudgetService budgetService;
    private final ChecklistCategoryService checklistCategoryService;
    private final PlaceService placeService;

    // ======= place ====== //
    @PostMapping("/trip/{tripId}/places")
    public Result<Long> savePlace(@PathVariable Long tripId, @RequestBody PlaceForm placeForm) {

        Place place =  PlaceConverter.formToEntity(placeForm);
        Long savedId = placeService.save(tripId, place);

        return new Result<>(savedId);
    }



    // ======= checklistCategory ======= //
    @PostMapping("/trip/{tripId}/checklist-categories")
    public Result<Long> saveCategory(@PathVariable Long tripId, @RequestBody ChecklistCategorySaveRequest categoryForm) {
        ChecklistCategory category = ChecklistCategoryConverter.formToEntity(categoryForm);
        Long savedId = checklistCategoryService.save(tripId, category);
        return new Result<>(savedId);
    }

    @GetMapping("/trip/{tripId}/checklist-categories")
    public ListResult<ChecklistCategoryDto> searchChecklistCategory(@PathVariable Long tripId) {
        List<ChecklistCategory> categories = checklistCategoryService.getListWithChecklist(tripId);

        List<ChecklistCategoryDto> result = ChecklistCategoryConverter.entityListToDtoList(categories);

        return new ListResult<ChecklistCategoryDto>(result.size(), result);
    }

    // ======= budgets ======= //
    @PostMapping("/trip/{tripId}/budgets")
    public Result<Long> saveBudget(@PathVariable Long tripId, @RequestBody BudgetSaveRequest budgetSaveRequest) {
        Budget budget = BudgetConverter.formToEntity(budgetSaveRequest);
        Long save = budgetService.save(tripId, budget);
        return new Result<>(save);
    }

    @GetMapping("/trip/{tripId}/budgets")
    public ListResult<BudgetSearchResponse> searchBudgetList(@PathVariable Long tripId) {
        List<Budget> budgets = budgetService.getList(tripId);

        List<BudgetSearchResponse> result = BudgetConverter.entityListToDtoList(budgets);

        return new ListResult<>(result.size(), result);
    }
}
