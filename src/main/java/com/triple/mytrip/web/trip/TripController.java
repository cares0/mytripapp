package com.triple.mytrip.web.trip;

import com.triple.mytrip.domain.budget.Budget;
import com.triple.mytrip.domain.budget.BudgetService;
import com.triple.mytrip.domain.checklistcategory.ChecklistCategory;
import com.triple.mytrip.domain.checklistcategory.ChecklistCategoryService;
import com.triple.mytrip.domain.schedule.Schedule;
import com.triple.mytrip.domain.schedule.ScheduleService;
import com.triple.mytrip.domain.flight.Flight;
import com.triple.mytrip.domain.flight.FlightService;
import com.triple.mytrip.domain.trip.Trip;
import com.triple.mytrip.domain.trip.TripService;
import com.triple.mytrip.web.budget.BudgetConverter;
import com.triple.mytrip.web.budget.response.BudgetSearchResponse;
import com.triple.mytrip.web.budget.request.BudgetSaveRequest;
import com.triple.mytrip.web.checklistcategory.ChecklistCategoryConverter;
import com.triple.mytrip.web.checklistcategory.response.ChecklistCategorySearchResponse;
import com.triple.mytrip.web.checklistcategory.request.ChecklistCategorySaveRequest;
import com.triple.mytrip.web.common.ListResult;
import com.triple.mytrip.web.common.Result;
import com.triple.mytrip.web.schedule.ScheduleConverter;
import com.triple.mytrip.web.flight.FlightConverter;
import com.triple.mytrip.web.flight.request.FlightSaveRequest;
import com.triple.mytrip.web.schedule.request.ScheduleSaveRequest;
import com.triple.mytrip.web.trip.request.TripEditRequest;
import com.triple.mytrip.web.trip.response.TripEditResponse;
import com.triple.mytrip.web.trip.response.TripSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;
    private final BudgetService budgetService;
    private final ChecklistCategoryService checklistCategoryService;
    private final ScheduleService scheduleService;
    private final FlightService flightService;

    // ======= trip ======= //
    @PatchMapping("/trips/{tripId}")
    public TripEditResponse editTrip(@PathVariable Long tripId, @RequestBody TripEditRequest tripEditRequest) {
        Trip trip = TripConverter.editRequestToEntity(tripEditRequest);
        Trip modified = tripService.edit(tripId, trip);
        TripEditResponse tripEditResponse = TripConverter.entityToEditResponse(modified);
        return tripEditResponse;
    }

    @DeleteMapping("/trips/{tripId}")
    public Result<String> deleteTrip(@PathVariable Long tripId) {
        tripService.delete(tripId);
        return new Result<>("success");
    }


    // ======= schedule ======= //
    // flight
    @PostMapping("/trips/{tripId}/schedules/flights")
    public Result<Map<String, Long>> saveFlight(@PathVariable Long tripId, @RequestBody FlightSaveRequest flightSaveRequest) {
        Flight flight = FlightConverter.saveRequestToEntity(flightSaveRequest);
        Map<String, Long> savedIdMap = flightService.save(tripId, flight);
        return new Result<>(savedIdMap);
    }

    @PostMapping("/trips/{tripId}/schedules/place/{placeId}")
    public Result<Long> saveSchedule(@PathVariable Long tripId, @PathVariable Long placeId, @RequestBody ScheduleSaveRequest scheduleSaveRequest) {
        Schedule schedule = ScheduleConverter.saveRequestToEntity(scheduleSaveRequest);
        Long savedId = scheduleService.save(tripId, placeId, schedule);
        return new Result<>(savedId);
    }

    @GetMapping("/trips/{tripId}/schedules")
    public TripSearchResponse searchWithSchedule(@PathVariable Long tripId) {
        Trip tripWithSchedule = tripService.getTripWithSchedule(tripId);

        return null;
    }

    // ======= checklistCategory ======= //
    @PostMapping("/trips/{tripId}/checklist-categories")
    public Result<Long> saveCategory(@PathVariable Long tripId, @RequestBody ChecklistCategorySaveRequest categoryRequest) {
        ChecklistCategory category = ChecklistCategoryConverter.saveRequestToEntity(categoryRequest);
        Long savedId = checklistCategoryService.save(tripId, category);
        return new Result<>(savedId);
    }

    @GetMapping("/trips/{tripId}/checklist-categories")
    public ListResult<ChecklistCategorySearchResponse> searchChecklistCategoryList(@PathVariable Long tripId) {
        List<ChecklistCategory> categories = checklistCategoryService.getListWithChecklist(tripId);
        List<ChecklistCategorySearchResponse> result = ChecklistCategoryConverter.entityListToDtoList(categories);
        return new ListResult<>(result.size(), result);
    }

    // ======= budgets ======= //
    @PostMapping("/trips/{tripId}/budgets")
    public Result<Long> saveBudget(@PathVariable Long tripId, @RequestBody BudgetSaveRequest budgetSaveRequest) {
        Budget budget = BudgetConverter.saveRequestToEntity(budgetSaveRequest);
        Long save = budgetService.store(tripId, budget);
        return new Result<>(save);
    }

    @GetMapping("/trips/{tripId}/budgets")
    public ListResult<BudgetSearchResponse> searchBudgetList(@PathVariable Long tripId) {
        List<Budget> budgets = budgetService.getList(tripId);
        List<BudgetSearchResponse> result = BudgetConverter.entityListToSearchResponseList(budgets);
        return new ListResult<>(result.size(), result);
    }
}
