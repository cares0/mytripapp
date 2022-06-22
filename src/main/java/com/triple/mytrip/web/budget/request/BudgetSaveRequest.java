package com.triple.mytrip.web.budget.request;

import com.triple.mytrip.domain.budget.Budget;
import com.triple.mytrip.domain.budget.PaymentPlan;
import com.triple.mytrip.domain.budget.BudgetCategory;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static lombok.AccessLevel.*;

@Setter @Getter
@NoArgsConstructor(access = PRIVATE)
public class BudgetSaveRequest {

    @NotNull
    private LocalDate date;

    @NotNull
    private PaymentPlan paymentPlan;

    @NotBlank
    private String content;

    @NotNull
    private BudgetCategory budgetCategory;

    @NotNull
    private Integer price;

    @NotNull
    private Integer order;
    private String place;

    public Budget toEntity() {
        return Budget.builder()
                .date(this.date)
                .paymentPlan(paymentPlan)
                .content(this.content)
                .budgetCategory(budgetCategory)
                .price(this.price)
                .order(this.order)
                .place(this.place)
                .build();
    }



}