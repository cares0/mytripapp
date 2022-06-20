package com.triple.mytrip.web.budget.response;

import com.triple.mytrip.web.budget.budgetfile.response.BudgetFileSearchResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class BudgetSearchResponse {

    private Long id;
    private Integer price;
    private String place;
    private LocalDate date;

    private Integer order;

    private String paymentPlan;
    private String tripCategory;
    private String content;

    public BudgetSearchResponse(Long id, Integer price, String place, LocalDate date, Integer order, String paymentPlan, String tripCategory, String content) {
        this.id = id;
        this.price = price;
        this.place = place;
        this.date = date;
        this.order = order;
        this.paymentPlan = paymentPlan;
        this.tripCategory = tripCategory;
        this.content = content;
    }

    private List<BudgetFileSearchResponse> budgetFiles;

    public void setBudgetFiles(List<BudgetFileSearchResponse> budgetFiles) {
        this.budgetFiles = budgetFiles;
    }
}
