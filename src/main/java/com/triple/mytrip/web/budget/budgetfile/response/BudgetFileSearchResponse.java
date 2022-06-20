package com.triple.mytrip.web.budget.budgetfile.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class BudgetFileSearchResponse {

    private Long id;
    private String oriName;
    private String fileName;
}
