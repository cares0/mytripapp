package com.triple.mytrip.web.budget.dto;

import com.triple.mytrip.domain.budget.PaymentPlan;
import com.triple.mytrip.domain.budget.budgetfile.BudgetFile;
import com.triple.mytrip.domain.common.TripCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter @Setter
public class BudgetDto {

    private Long id;
    private Integer price;
    private String place;
    private LocalDate date;

    private Integer order;

    private List<BudgetFileDto> budgetFiles;
    private PaymentPlan paymentPlan;
    private TripCategory tripCategory;
    private String content;

}
