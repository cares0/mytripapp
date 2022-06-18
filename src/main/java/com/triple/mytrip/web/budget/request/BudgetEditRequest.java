package com.triple.mytrip.web.budget.request;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BudgetEditRequest {

    private BudgetEditAll budgetEditAll;
    private BudgetEditOrderAndDate budgetEditOrderAndDate;

}
