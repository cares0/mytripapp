package com.triple.mytrip.web.budget.response;

import com.triple.mytrip.domain.budget.Budget;
import com.triple.mytrip.web.budget.budgetfile.response.BudgetFileSearchResponse;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter @Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BudgetSearchResponse {

    private Long id;
    private Integer price;
    private String place;
    private LocalDate date;

    private Integer order;

    private String paymentPlan;
    private String tripCategory;
    private String content;

    private List<BudgetFileSearchResponse> budgetFiles;

    public static BudgetSearchResponse toResponse(Budget budget) {
        if (Objects.isNull(budget)) {
            return null;
        }

        String tripCategory = Objects.isNull(budget.getTripCategory()) ?
                null : budget.getTripCategory().getKorName();

        String paymentPlan = Objects.isNull(budget.getPaymentPlan()) ?
                null : budget.getPaymentPlan().getKorName();

        List<BudgetFileSearchResponse> budgetFileSearchResponses =
                budget.getBudgetFiles().stream().map(budgetFile ->
                                BudgetFileSearchResponse.toResponse(budgetFile))
                        .collect(Collectors.toList());

        return BudgetSearchResponse.builder()
                .id(budget.getId())
                .price(budget.getPrice())
                .place(budget.getPlace())
                .date(budget.getDate())
                .order(budget.getOrder())
                .paymentPlan(paymentPlan)
                .tripCategory(tripCategory)
                .content(budget.getContent())
                .budgetFiles(budgetFileSearchResponses)
                .build();
    }

}
