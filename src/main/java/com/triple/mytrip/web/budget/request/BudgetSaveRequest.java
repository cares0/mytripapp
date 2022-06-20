package com.triple.mytrip.web.budget.request;

import com.triple.mytrip.domain.budget.PaymentPlan;
import com.triple.mytrip.domain.budget.TripCategory;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter @Getter
public class BudgetSaveRequest {

    private LocalDate date;
    private PaymentPlan paymentPlan;
    private String content;
    private TripCategory tripCategory;
    private Integer price;
    private Integer order;
    private String place;

}
