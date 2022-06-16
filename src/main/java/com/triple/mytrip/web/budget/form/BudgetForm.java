package com.triple.mytrip.web.budget.form;

import com.triple.mytrip.domain.budget.PaymentPlan;
import com.triple.mytrip.domain.common.TripCategory;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter @Getter
public class BudgetForm {

    private LocalDate date;
    private PaymentPlan paymentPlan;
    private String content;
    private TripCategory tripCategory;
    private Integer price;
    private Integer budgetOrder;
    private String place;

}
