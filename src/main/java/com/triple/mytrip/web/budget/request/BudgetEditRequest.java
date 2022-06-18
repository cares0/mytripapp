package com.triple.mytrip.web.budget.request;

import com.triple.mytrip.domain.budget.PaymentPlan;
import com.triple.mytrip.domain.common.TripCategory;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class BudgetEditRequest {

    private LocalDate date;
    private PaymentPlan paymentPlan;
    private String content;
    private TripCategory tripCategory;
    private Integer price;
    private String place;
    private Integer order;

}
