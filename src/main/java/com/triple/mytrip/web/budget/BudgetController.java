package com.triple.mytrip.web.budget;

import com.triple.mytrip.domain.budget.Budget;
import com.triple.mytrip.domain.budget.BudgetService;
import com.triple.mytrip.web.budget.form.BudgetForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BudgetController {

    private final BudgetService budgetService;

    @PostMapping("/budgets")
    public Long save(@RequestBody BudgetForm budgetForm) {
        Budget budget = convertBudget(budgetForm);
        return budgetService.save(budget, budgetForm.getTripId());
    }

    private Budget convertBudget(BudgetForm budgetSaveForm) {
        return new Budget(budgetSaveForm.getTripCategory(), budgetSaveForm.getPrice(),
                budgetSaveForm.getDate(), budgetSaveForm.getPaymentPlan(),
                budgetSaveForm.getBudgetOrder(), budgetSaveForm.getPlace(),
                budgetSaveForm.getContent());
    }
}
