package com.triple.mytrip.web.budget;

import com.triple.mytrip.domain.budget.Budget;
import com.triple.mytrip.domain.budget.BudgetService;
import com.triple.mytrip.web.budget.dto.BudgetDto;
import com.triple.mytrip.web.budget.form.BudgetForm;
import com.triple.mytrip.web.common.ListResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BudgetController {

    private final BudgetService budgetService;

    @PostMapping("/budgets")
    public Long save(@RequestBody BudgetForm budgetForm) {
        Budget budget = formToBudget(budgetForm);
        return budgetService.save(budget, budgetForm.getTripId());
    }

    @GetMapping("/budgets/{tripId}")
    public ListResult<BudgetDto> searchList(@PathVariable Long tripId) {
        List<Budget> budgets = budgetService.getList(tripId);

        List<BudgetDto> result = budgetListToDtoList(budgets);

        return new ListResult<BudgetDto>(0, result);
    }

    private List<BudgetDto> budgetListToDtoList(List<Budget> list) {
        return list.stream().map(
                        (budget) -> new BudgetDto(
                                budget.getId(),
                                budget.getPrice(),
                                budget.getPlace(),
                                budget.getDate(),
                                budget.getBudgetOrder(),
                                budget.getTripCategory(),
                                budget.getPaymentPlan(),
                                budget.getContent()))
                .collect(Collectors.toList());
    }

    private Budget formToBudget(BudgetForm budgetSaveForm) {
        return new Budget(budgetSaveForm.getTripCategory(),
                budgetSaveForm.getPrice(),
                budgetSaveForm.getDate(),
                budgetSaveForm.getPaymentPlan(),
                budgetSaveForm.getBudgetOrder(),
                budgetSaveForm.getPlace(),
                budgetSaveForm.getContent());
    }


}
