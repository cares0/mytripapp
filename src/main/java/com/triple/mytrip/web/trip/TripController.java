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
import com.triple.mytrip.web.exception.ValidationFailException;
import com.triple.mytrip.web.flight.request.FlightSaveRequest;
import com.triple.mytrip.web.schedule.request.ScheduleSaveRequest;
import com.triple.mytrip.web.trip.request.TripEditRequest;
import com.triple.mytrip.web.trip.response.TripEditResponse;
import com.triple.mytrip.web.trip.response.TripSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    // ======= trip ======= //
    @GetMapping("/trips/{tripId}")
    public TripSearchResponse tripDetail(@PathVariable Long tripId) {
        Trip trip = tripService.getOne(tripId);
        return TripSearchResponse.toResponse(trip);
    }

    @PatchMapping("/trips/{tripId}")
    public TripEditResponse tripModify(@PathVariable Long tripId, @RequestBody TripEditRequest tripEditRequest) {
        Trip modified = tripEditRequest.toEntity();
        modified = tripService.modify(tripId, modified);
        return TripEditResponse.toResponse(modified);
    }

    @DeleteMapping("/trips/{tripId}")
    public Result<Long> tripRemove(@PathVariable Long tripId) {
        tripService.remove(tripId);
        return new Result<>(tripId);
    }

    // ======= schedule ======= //
    @PostMapping("/trips/{tripId}/schedules/flights")
    public Result<Map<String, Long>> flightScheduleAdd(@PathVariable Long tripId, @RequestBody FlightSaveRequest flightSaveRequest) {
        Flight flight = flightSaveRequest.toEntity();
        Map<String, Long> savedIdMap = tripService.addFlightSchedule(tripId, flight);
        return new Result<>(savedIdMap);
    }

    @PostMapping("/trips/{tripId}/schedules/places/{placeId}")
    public Result<Long> placeScheduleAdd(@PathVariable Long tripId, @PathVariable Long placeId, @RequestBody ScheduleSaveRequest scheduleSaveRequest) {
        Schedule schedule = scheduleSaveRequest.toEntity();
        Long savedId = tripService.addPlaceSchedule(tripId, placeId, schedule);
        return new Result<>(savedId);
    }

    @GetMapping("/trips/{tripId}/schedules")
    public TripSearchResponse tripDetailWithSchedule(@PathVariable Long tripId) {
        Trip trip = tripService.getOneWithSchedule(tripId);
        return TripSearchResponse.toResponse(trip);
    }

    // ======= checklistCategory ======= //
    @PostMapping("/trips/{tripId}/checklist-categories")
    public Result<Long> checklistCategoryAdd(@PathVariable Long tripId, @RequestBody ChecklistCategorySaveRequest categoryRequest) {
        ChecklistCategory checklistCategory = categoryRequest.toEntity();
        Long savedId = tripService.addChecklistCategory(tripId, checklistCategory);
        return new Result<>(savedId);
    }

    @GetMapping("/trips/{tripId}/checklist-categories")
    public TripSearchResponse tripDetailWithChecklistCategory(@PathVariable Long tripId) {
        Trip trip = tripService.getOneWithChecklistCategory(tripId);
        return TripSearchResponse.toResponse(trip);
    }

    // ======= budgets ======= //
    @PostMapping("/trips/{tripId}/budgets")
    public Result<Long> budgetAdd(@PathVariable Long tripId, @Validated @RequestBody BudgetSaveRequest budgetSaveRequest, BindingResult bindingResult) {
        validate(bindingResult);

        Budget budget = budgetSaveRequest.toEntity();
        Long save = tripService.addBudget(tripId, budget);
        return new Result<>(save);
    }

    @GetMapping("/trips/{tripId}/budgets")
    public TripSearchResponse tripDetailWithBudget(@PathVariable Long tripId) {
        Trip trip = tripService.getOneWithBudget(tripId);
        return TripSearchResponse.toResponse(trip);
    }

    private void validate(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> map = new HashMap<>();
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                String defaultMessage = objectError.getDefaultMessage();
                FieldError fieldError = (FieldError) objectError;
                String fieldName = fieldError.getField();
                map.put(fieldName, defaultMessage);
            }
            throw new ValidationFailException(map.toString());
        }
    }
}
