package com.triple.mytrip.web.budget.dto;

import com.triple.mytrip.domain.budget.PaymentPlan;
import com.triple.mytrip.domain.common.TripCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter @Setter
public class BudgetDto {

    Long id;
    Integer price;
    String place;
    LocalDate date;
    Integer order;

    TripCategory tripCategory;
    PaymentPlan paymentPlan;
    String content;

}
