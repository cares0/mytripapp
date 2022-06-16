package com.triple.mytrip.web.budget;

import com.triple.mytrip.domain.budget.Budget;
import com.triple.mytrip.web.budget.dto.BudgetDto;
import com.triple.mytrip.web.budget.form.BudgetForm;

import java.util.List;
import java.util.stream.Collectors;

public class BudgetConverter {

    public static BudgetDto budgetToDto(Budget budget) {

        return new BudgetDto(budget.getId(),
                budget.getPrice(),
                budget.getPlace(),
                budget.getDate(),
                budget.getBudgetOrder(),
                budget.getTripCategory(),
                budget.getPaymentPlan(),
                budget.getContent());
    }

    public static List<BudgetDto> budgetListToDtoList(List<Budget> list) {
        return list.stream().map(
                        (budget) -> budgetToDto(budget))
                .collect(Collectors.toList());
    }

    public static Budget formToBudget(BudgetForm budgetSaveForm) {
        return new Budget(
                budgetSaveForm.getTripCategory(),
                budgetSaveForm.getPrice(),
                budgetSaveForm.getDate(),
                budgetSaveForm.getPaymentPlan(),
                budgetSaveForm.getBudgetOrder(),
                budgetSaveForm.getPlace(),
                budgetSaveForm.getContent());
    }
}
