package com.triple.mytrip.web.budget.request;

import com.triple.mytrip.domain.budget.Budget;
import com.triple.mytrip.domain.budget.PaymentPlan;
import com.triple.mytrip.domain.budget.TripCategory;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BudgetEditRequest {

    private LocalDate date;
    private PaymentPlan paymentPlan;
    private String content;
    private TripCategory tripCategory;
    private Integer price;
    private String place;
    private Integer order;

    public Budget toEntity() {
        return Budget.builder()
                .date(this.date)
                .paymentPlan(this.paymentPlan)
                .content(this.content)
                .tripCategory(this.tripCategory)
                .price(this.price)
                .place(this.place)
                .order(this.order)
                .build();
    }

}
