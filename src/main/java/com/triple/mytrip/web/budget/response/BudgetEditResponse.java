package com.triple.mytrip.web.budget.response;

import com.triple.mytrip.domain.budget.PaymentPlan;
import com.triple.mytrip.domain.common.TripCategory;
import lombok.*;

import java.time.LocalDate;

@Setter @Getter
@AllArgsConstructor
public class BudgetEditResponse {

    private Long id;
    private TripCategory tripCategory;
    private Integer price;
    private LocalDate date;
    private PaymentPlan paymentPlan;
    private Integer order;
    private String place;
    private String content;

}
