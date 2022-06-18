package com.triple.mytrip.web.budget.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class BudgetEditOrderAndDate {

    private Integer order;
    private LocalDate date;
}
