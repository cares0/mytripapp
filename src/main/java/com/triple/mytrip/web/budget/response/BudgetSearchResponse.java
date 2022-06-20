package com.triple.mytrip.web.budget.response;

import com.triple.mytrip.domain.budget.Budget;
import com.triple.mytrip.web.budget.budgetfile.response.BudgetFileSearchResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    @Builder
    public BudgetSearchResponse(Long id, Integer price, String place, LocalDate date, Integer order, String paymentPlan, String tripCategory, String content, List<BudgetFileSearchResponse> budgetFiles) {
        this.id = id;
        this.price = price;
        this.place = place;
        this.date = date;
        this.order = order;
        this.paymentPlan = paymentPlan;
        this.tripCategory = tripCategory;
        this.content = content;
        this.budgetFiles = budgetFiles;
    }

    private List<BudgetFileSearchResponse> budgetFiles;

    public void setBudgetFiles(List<BudgetFileSearchResponse> budgetFiles) {
        this.budgetFiles = budgetFiles;
    }

    public static BudgetSearchResponse toResponse(Budget budget) {
        return BudgetSearchResponse.builder()
                .id(budget.getId())
                .price(budget.getPrice())
                .place(budget.getPlace())
                .date(budget.getDate())
                .order(budget.getOrder())
                .paymentPlan(budget.getPaymentPlan().getKorName())
                .tripCategory(budget.getTripCategory().getKorName())
                .content(budget.getContent())
                .budgetFiles(budget.getBudgetFiles().stream().map(budgetFile -> BudgetFileSearchResponse.toResponse(budgetFile)).collect(Collectors.toList()))
                .build();
    }
}
