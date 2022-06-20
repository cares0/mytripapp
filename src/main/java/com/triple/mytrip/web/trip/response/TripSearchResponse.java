package com.triple.mytrip.web.trip.response;

import com.triple.mytrip.web.budget.response.BudgetSearchResponse;
import com.triple.mytrip.web.checklistcategory.response.ChecklistCategorySearchResponse;
import com.triple.mytrip.web.schedule.response.ScheduleSearchResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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

    public TripSearchResponse(Long id, String city, String title, LocalDate arrivalDate, LocalDate departureDate, String partner, String tripStyle) {
        this.id = id;
        this.city = city;
        this.title = title;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.partner = partner;
        this.tripStyle = tripStyle;
    }

    private List<ScheduleSearchResponse> schedules;
    private List<BudgetSearchResponse> budgets;
    private List<ChecklistCategorySearchResponse> checklistCategory;
}
