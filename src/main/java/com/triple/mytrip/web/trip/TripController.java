package com.triple.mytrip.web.trip;

import com.triple.mytrip.domain.budget.Budget;
import com.triple.mytrip.domain.checklistcategory.ChecklistCategory;
import com.triple.mytrip.domain.schedule.Schedule;
import com.triple.mytrip.domain.flight.Flight;
import com.triple.mytrip.domain.trip.Trip;
import com.triple.mytrip.domain.trip.TripService;
import com.triple.mytrip.web.budget.request.BudgetSaveRequest;
import com.triple.mytrip.web.checklistcategory.request.ChecklistCategorySaveRequest;
import com.triple.mytrip.web.common.Result;
import com.triple.mytrip.web.util.ValidationUtils;
import com.triple.mytrip.web.flight.request.FlightSaveRequest;
import com.triple.mytrip.web.schedule.request.ScheduleSaveRequest;
import com.triple.mytrip.web.trip.request.TripEditRequest;
import com.triple.mytrip.web.trip.response.TripEditResponse;
import com.triple.mytrip.web.trip.response.TripSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trips")
public class TripController {

    private final TripService tripService;
    private final ValidationUtils validationUtils;

    // ======= trip ======= //
    @GetMapping("/{tripId}")
    public TripSearchResponse tripDetail(@PathVariable Long tripId) {
        Trip trip = tripService.getOne(tripId);
        return TripSearchResponse.toResponse(trip);
    }

    @PatchMapping("/{tripId}")
    public TripEditResponse tripModify(@PathVariable Long tripId,
                                       @RequestBody TripEditRequest tripEditRequest) {
        Trip modified = tripEditRequest.toEntity();
        modified = tripService.modify(tripId, modified);
        return TripEditResponse.toResponse(modified);
    }

    @DeleteMapping("/{tripId}")
    public Result<Long> tripRemove(@PathVariable Long tripId) {
        tripService.remove(tripId);
        return new Result<>(tripId);
    }

    // ======= schedule ======= //
    @PostMapping("/{tripId}/schedules/flights")
    public Result<Map<String, Long>> flightScheduleAdd(@PathVariable Long tripId,
                                                       @Validated @RequestBody FlightSaveRequest flightSaveRequest,
                                                       BindingResult bindingResult) {
        validationUtils.validate(bindingResult);

        Flight flight = flightSaveRequest.toEntity();
        Map<String, Long> savedIdMap = tripService.addFlightSchedule(tripId, flight);
        return new Result<>(savedIdMap);
    }

    @PostMapping("/{tripId}/schedules/places/{placeId}")
    public Result<Long> placeScheduleAdd(@PathVariable Long tripId,
                                         @PathVariable Long placeId,
                                         @Validated @RequestBody ScheduleSaveRequest scheduleSaveRequest,
                                         BindingResult bindingResult) {
        validationUtils.validate(bindingResult);

        Schedule schedule = scheduleSaveRequest.toEntity();
        Long savedId = tripService.addPlaceSchedule(tripId, placeId, schedule);
        return new Result<>(savedId);
    }

    @GetMapping("/{tripId}/schedules")
    public TripSearchResponse tripDetailWithSchedule(@PathVariable Long tripId) {
        Trip trip = tripService.getOneWithSchedule(tripId);
        return TripSearchResponse.toResponse(trip);
    }

    // ======= checklistCategory ======= //
    @PostMapping("/{tripId}/checklist-categories")
    public Result<Long> checklistCategoryAdd(@PathVariable Long tripId,
                                             @Validated @RequestBody ChecklistCategorySaveRequest categoryRequest,
                                             BindingResult bindingResult) {
        validationUtils.validate(bindingResult);

        ChecklistCategory checklistCategory = categoryRequest.toEntity();
        Long savedId = tripService.addChecklistCategory(tripId, checklistCategory);
        return new Result<>(savedId);
    }

    @GetMapping("/{tripId}/checklist-categories")
    public TripSearchResponse tripDetailWithChecklistCategory(@PathVariable Long tripId) {
        Trip trip = tripService.getOneWithChecklistCategory(tripId);
        return TripSearchResponse.toResponse(trip);
    }

    // ======= budgets ======= //
    @PostMapping("/{tripId}/budgets")
    public Result<Long> budgetAdd(@PathVariable Long tripId,
                                  @Validated @RequestBody BudgetSaveRequest budgetSaveRequest,
                                  BindingResult bindingResult) {
        validationUtils.validate(bindingResult);

        Budget budget = budgetSaveRequest.toEntity();
        Long save = tripService.addBudget(tripId, budget);
        return new Result<>(save);
    }

    @GetMapping("/{tripId}/budgets")
    public TripSearchResponse tripDetailWithBudget(@PathVariable Long tripId) {
        Trip trip = tripService.getOneWithBudget(tripId);
        return TripSearchResponse.toResponse(trip);
    }

}
