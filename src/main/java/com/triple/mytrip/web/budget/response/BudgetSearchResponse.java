package com.triple.mytrip.web.budget.response;

import com.triple.mytrip.domain.budget.Budget;
import com.triple.mytrip.web.budget.budgetfile.response.BudgetFileSearchResponse;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static lombok.AccessLevel.*;

@Getter @Setter
@Builder
@AllArgsConstructor(access = PRIVATE)
public class BudgetSearchResponse {

    private Long id;
    private Integer price;
    private String place;
    private LocalDate date;

    private Integer order;

    private String paymentPlan;
    private String budgetCategory;
    private String content;

    private List<BudgetFileSearchResponse> budgetFiles;

    public static BudgetSearchResponse toResponse(Budget budget) {
        if (Objects.isNull(budget)) {
            return null;
        }

        String budgetCategory = Objects.isNull(budget.getBudgetCategory()) ?
                null : budget.getBudgetCategory().getKorName();

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
                .budgetCategory(budgetCategory)
                .content(budget.getContent())
                .budgetFiles(budgetFileSearchResponses)
                .build();
    }

}
