package com.triple.mytrip.web.trip.response;

import com.triple.mytrip.domain.budget.Budget;
import com.triple.mytrip.domain.checklistcategory.ChecklistCategory;
import com.triple.mytrip.domain.schedule.Schedule;
import com.triple.mytrip.domain.trip.Trip;
import com.triple.mytrip.web.budget.response.BudgetSearchResponse;
import com.triple.mytrip.web.checklistcategory.response.ChecklistCategorySearchResponse;
import com.triple.mytrip.web.schedule.response.ScheduleSearchResponse;
import lombok.*;
import org.hibernate.LazyInitializationException;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static lombok.AccessLevel.*;

@Getter @Setter
@Builder
@AllArgsConstructor(access = PRIVATE)
public class TripSearchResponse {

    private Long id;
    private String city;
    private String title;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private String partner;
    private String tripStyle;

    private List<BudgetSearchResponse> budgets;
    private List<ChecklistCategorySearchResponse> checklistCategories;
    private List<ScheduleSearchResponse> schedules;

    public static TripSearchResponse toResponse(Trip trip) {
        String partner = Objects.isNull(trip.getPartner()) ?
                null : trip.getPartner().getKorName();

        String tripStyle = Objects.isNull(trip.getTripStyle()) ?
                null : trip.getTripStyle().getKorName();

        LocalDate arrivalDate = null;
        LocalDate departureDate = null;
        if (Objects.nonNull(trip.getPeriod())) {
            arrivalDate = trip.getPeriod().getArrivalDate();
            departureDate = trip.getPeriod().getDepartureDate();
        }
        
        List<BudgetSearchResponse> budgets =
                getBudgetSearchResponses(trip.getBudgets());

        List<ChecklistCategorySearchResponse> checklistCategories =
                getChecklistCategorySearchResponses(trip.getChecklistCategories());

        List<ScheduleSearchResponse> schedules =
                getScheduleSearchResponses(trip.getSchedules());

        return TripSearchResponse.builder()
                .id(trip.getId())
                .city(trip.getCity())
                .title(trip.getTitle())
                .arrivalDate(arrivalDate)
                .departureDate(departureDate)
                .partner(partner)
                .tripStyle(tripStyle)
                .budgets(budgets)
                .checklistCategories(checklistCategories)
                .schedules(schedules)
                .build();
    }

    private static List<BudgetSearchResponse> getBudgetSearchResponses(List<Budget> budgets) {
        try {
            return CollectionUtils.isEmpty(budgets) ?
                    null : budgets.stream().map(budget ->
                            BudgetSearchResponse.toResponse(budget))
                    .collect(Collectors.toList());
        } catch (LazyInitializationException e) {
            return null;
        }
    }

    private static List<ChecklistCategorySearchResponse> getChecklistCategorySearchResponses(List<ChecklistCategory> checklistCategories) {
        try {
            return CollectionUtils.isEmpty(checklistCategories) ?
                    null : checklistCategories.stream().map(checklistCategory ->
                            ChecklistCategorySearchResponse.toResponse(checklistCategory))
                    .collect(Collectors.toList());
        } catch (LazyInitializationException e) {
            return null;
        }
    }

    private static List<ScheduleSearchResponse> getScheduleSearchResponses(List<Schedule> schedules) {
        try {
            return CollectionUtils.isEmpty(schedules) ?
                    null : schedules.stream().map(schedule ->
                            ScheduleSearchResponse.toResponse(schedule))
                    .collect(Collectors.toList());
        } catch (LazyInitializationException e) {
            return null;
        }
    }

}
