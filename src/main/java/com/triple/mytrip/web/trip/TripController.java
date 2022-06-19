package com.triple.mytrip.web.trip;

import com.triple.mytrip.domain.budget.Budget;
import com.triple.mytrip.domain.budget.BudgetService;
import com.triple.mytrip.domain.checklist.category.ChecklistCategory;
import com.triple.mytrip.domain.checklist.category.ChecklistCategoryService;
import com.triple.mytrip.domain.schedule.Schedule;
import com.triple.mytrip.domain.schedule.ScheduleService;
import com.triple.mytrip.web.budget.BudgetConverter;
import com.triple.mytrip.web.budget.response.BudgetSearchResponse;
import com.triple.mytrip.web.budget.request.BudgetSaveRequest;
import com.triple.mytrip.web.checklist.ChecklistCategoryConverter;
import com.triple.mytrip.web.checklist.response.ChecklistCategorySearchResponse;
import com.triple.mytrip.web.checklist.request.ChecklistCategoryRequest;
import com.triple.mytrip.web.common.ListResult;
import com.triple.mytrip.web.common.Result;
import com.triple.mytrip.web.schedule.PlaceConverter;
import com.triple.mytrip.web.schedule.form.PlaceForm;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TripController {

    private final BudgetService budgetService;
    private final ChecklistCategoryService checklistCategoryService;
    private final ScheduleService scheduleService;

    // ======= place ====== //
    @PostMapping("/trip/{tripId}/places")
    public Result<Long> savePlace(@PathVariable Long tripId, @RequestBody PlaceForm placeForm) {

        Schedule schedule =  PlaceConverter.formToEntity(placeForm);
        Long savedId = scheduleService.save(tripId, schedule);

        return new Result<>(savedId);
    }

    @GetMapping("/trip/{tripId}/places")
    public void searchPlace(@PathVariable Long tripId) {
        scheduleService.getList(tripId);
    }



    // ======= checklistCategory ======= //
    @PostMapping("/trip/{tripId}/checklist-categories")
    public Result<Long> saveCategory(@PathVariable Long tripId, @RequestBody ChecklistCategoryRequest categoryRequest) {
        ChecklistCategory category = ChecklistCategoryConverter.saveRequestToEntity(categoryRequest);
        Long savedId = checklistCategoryService.save(tripId, category);
        return new Result<>(savedId);
    }

    @GetMapping("/trip/{tripId}/checklist-categories")
    public ListResult<ChecklistCategorySearchResponse> searchChecklistCategory(@PathVariable Long tripId) {
        List<ChecklistCategory> categories = checklistCategoryService.getListWithChecklist(tripId);

        List<ChecklistCategorySearchResponse> result = ChecklistCategoryConverter.entityListToDtoList(categories);

        return new ListResult<>(result.size(), result);
    }

    // ======= budgets ======= //
    @PostMapping("/trip/{tripId}/budgets")
    public Result<Long> saveBudget(@PathVariable Long tripId, @RequestBody BudgetSaveRequest budgetSaveRequest) {
        Budget budget = BudgetConverter.saveRequestToEntity(budgetSaveRequest);
        Long save = budgetService.save(tripId, budget);
        return new Result<>(save);
    }

    @GetMapping("/trip/{tripId}/budgets")
    public ListResult<BudgetSearchResponse> searchBudgetList(@PathVariable Long tripId) {
        List<Budget> budgets = budgetService.getList(tripId);

        List<BudgetSearchResponse> result = BudgetConverter.entityListToSearchResponseList(budgets);

        return new ListResult<>(result.size(), result);
    }
}
