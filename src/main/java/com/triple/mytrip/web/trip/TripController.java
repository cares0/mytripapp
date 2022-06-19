package com.triple.mytrip.web.trip;

import com.triple.mytrip.domain.budget.Budget;
import com.triple.mytrip.domain.budget.BudgetService;
import com.triple.mytrip.domain.checklist.category.ChecklistCategory;
import com.triple.mytrip.domain.checklist.category.ChecklistCategoryService;
import com.triple.mytrip.domain.schedule.Schedule;
import com.triple.mytrip.domain.schedule.ScheduleService;
import com.triple.mytrip.domain.schedule.flight.Flight;
import com.triple.mytrip.domain.schedule.flight.FlightService;
import com.triple.mytrip.web.budget.BudgetConverter;
import com.triple.mytrip.web.budget.response.BudgetSearchResponse;
import com.triple.mytrip.web.budget.request.BudgetSaveRequest;
import com.triple.mytrip.web.checklist.ChecklistCategoryConverter;
import com.triple.mytrip.web.checklist.response.ChecklistCategorySearchResponse;
import com.triple.mytrip.web.checklist.request.ChecklistCategoryRequest;
import com.triple.mytrip.web.common.ListResult;
import com.triple.mytrip.web.common.Result;
import com.triple.mytrip.web.schedule.flight.request.FlightSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.triple.mytrip.web.schedule.flight.FlightConverter.saveRequestToEntity;

@RestController
@RequiredArgsConstructor
public class TripController {

    private final BudgetService budgetService;
    private final ChecklistCategoryService checklistCategoryService;
    private final ScheduleService scheduleService;
    private final FlightService flightService;

    // ======= schedule ======= //
    // flight
    @PostMapping("/trip/{tripId}/schedules/flights")
    public Result<Map<String, Long>> save(@PathVariable Long tripId, @RequestBody FlightSaveRequest flightSaveRequest) {
        Flight flight = saveRequestToEntity(flightSaveRequest);

        Map<String, Long> save = flightService.save(tripId, flight);

        return new Result<>(save);
    }

    // ======= place ====== //


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
