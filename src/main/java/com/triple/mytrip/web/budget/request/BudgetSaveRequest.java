package com.triple.mytrip.web.budget.request;

import com.triple.mytrip.domain.budget.Budget;
import com.triple.mytrip.domain.budget.PaymentPlan;
import com.triple.mytrip.domain.budget.BudgetCategory;
import lombok.*;

import java.time.LocalDate;

import static lombok.AccessLevel.*;

@Setter @Getter
@Builder
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
public class BudgetSaveRequest {

    private LocalDate date;
    private PaymentPlan paymentPlan;
    private String content;
    private BudgetCategory budgetCategory;
    private Integer price;
    private Integer order;
    private String place;

    public Budget toEntity() {
        return Budget.builder()
                .date(this.date)
                .paymentPlan(this.paymentPlan)
                .content(this.content)
                .budgetCategory(this.budgetCategory)
                .price(this.price)
                .order(this.order)
                .place(this.place)
                .build();
    }

}