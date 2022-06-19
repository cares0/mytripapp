package com.triple.mytrip.web.budget.response;

import com.triple.mytrip.domain.budget.PaymentPlan;
import com.triple.mytrip.domain.common.TripCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter @Setter
public class BudgetSearchResponse {

    private Long id;
    private Integer price;
    private String place;
    private LocalDate date;

    private Integer order;

    private PaymentPlan paymentPlan;
    private TripCategory tripCategory;
    private String content;
    private List<BudgetFileSearchResponse> budgetFiles;

}
