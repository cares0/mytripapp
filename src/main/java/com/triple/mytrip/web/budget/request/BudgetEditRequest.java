package com.triple.mytrip.web.budget.request;

import com.triple.mytrip.domain.budget.Budget;
import com.triple.mytrip.domain.budget.BudgetCategory;
import com.triple.mytrip.domain.budget.PaymentPlan;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

import static lombok.AccessLevel.*;

@Getter @Setter
@NoArgsConstructor(access = PRIVATE)
public class BudgetEditRequest {

    private LocalDate date;
    private PaymentPlan paymentPlan;
    private String content;
    private BudgetCategory budgetCategory;

    @Max(value = 10000000)
    @Min(value = 10)
    private Integer price;
    private String place;
    private Integer order;

    public Budget toEntity() {
        return Budget.builder()
                .date(this.date)
                .paymentPlan(this.paymentPlan)
                .content(this.content)
                .budgetCategory(this.budgetCategory)
                .price(this.price)
                .place(this.place)
                .order(this.order)
                .build();
    }

}
