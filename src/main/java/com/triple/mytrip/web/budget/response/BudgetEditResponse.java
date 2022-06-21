package com.triple.mytrip.web.budget.response;

import com.triple.mytrip.domain.budget.Budget;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Setter @Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BudgetEditResponse {

    private Long id;
    private String budgetCategory;
    private Integer price;
    private LocalDate date;
    private String paymentPlan;
    private Integer order;
    private String place;
    private String content;

    public static BudgetEditResponse toResponse(Budget budget) {
        String paymentPlan = Objects.isNull(budget.getPaymentPlan()) ?
                null : budget.getPaymentPlan().getKorName();

        String budgetCategory = Objects.isNull(budget.getBudgetCategory()) ?
                null : budget.getBudgetCategory().getKorName();

        return BudgetEditResponse.builder()
               .id(budget.getId())
               .budgetCategory(budgetCategory)
               .price(budget.getPrice())
               .date(budget.getDate())
               .paymentPlan(paymentPlan)
               .order(budget.getOrder())
               .place(budget.getPlace())
               .content(budget.getContent())
               .build();
    }

}
