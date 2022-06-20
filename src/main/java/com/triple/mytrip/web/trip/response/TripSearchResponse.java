package com.triple.mytrip.web.trip.response;

import com.triple.mytrip.domain.trip.Trip;
import com.triple.mytrip.web.budget.response.BudgetSearchResponse;
import com.triple.mytrip.web.checklistcategory.response.ChecklistCategorySearchResponse;
import com.triple.mytrip.web.schedule.response.ScheduleSearchResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
public class TripSearchResponse {

    private Long id;
    private String city;
    private String title;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private String partner;
    private String tripStyle;

    @Builder
    public TripSearchResponse(Long id, String city, String title, LocalDate arrivalDate, LocalDate departureDate, String partner, String tripStyle, List<ScheduleSearchResponse> schedules, List<BudgetSearchResponse> budgets, List<ChecklistCategorySearchResponse> checklistCategory) {
        this.id = id;
        this.city = city;
        this.title = title;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.partner = partner;
        this.tripStyle = tripStyle;
        this.schedules = schedules;
        this.budgets = budgets;
        this.checklistCategories = checklistCategory;
    }

    private List<ScheduleSearchResponse> schedules;
    private List<BudgetSearchResponse> budgets;
    private List<ChecklistCategorySearchResponse> checklistCategories;

    public static TripSearchResponse toResponse(Trip trip) {
        return TripSearchResponse.builder()
                .id(trip.getId())
                .city(trip.getCity())
                .title(trip.getTitle())
                .arrivalDate(trip.getArrivalDate())
                .departureDate(trip.getDepartureDate())
                .partner(Objects.isNull(trip.getPartner()) ? null : trip.getPartner().getKorName())
                .tripStyle(Objects.isNull(trip.getTripStyle()) ? null : trip.getTripStyle().getKorName())
                .schedules(trip.getSchedules().stream().map(schedule -> ScheduleSearchResponse.toResponse(schedule)).collect(Collectors.toList()))
                .budgets(trip.getBudgets().stream().map(budget -> BudgetSearchResponse.toResponse(budget)).collect(Collectors.toList()))
                .build();
    }
}
