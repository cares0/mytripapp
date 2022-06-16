package com.triple.mytrip.web.trip;

import com.triple.mytrip.domain.budget.Budget;
import com.triple.mytrip.domain.budget.BudgetService;
import com.triple.mytrip.web.budget.BudgetConverter;
import com.triple.mytrip.web.budget.dto.BudgetDto;
import com.triple.mytrip.web.budget.form.BudgetForm;
import com.triple.mytrip.web.common.ListResult;
import com.triple.mytrip.web.common.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TripController {

    private final BudgetService budgetService;


    // ======= budgets ======= //
    @PostMapping("/trip/{tripId}/budgets")
    public Result<Long> save(@PathVariable Long tripId, @RequestBody BudgetForm budgetForm) {
        Budget budget = BudgetConverter.formToBudget(budgetForm);
        Long save = budgetService.save(tripId, budget);
        return new Result<>(save);
    }

    @GetMapping("/trip/{tripId}/budgets")
    public ListResult<BudgetDto> searchList(@PathVariable Long tripId) {
        List<Budget> budgets = budgetService.getList(tripId);

        List<BudgetDto> result = BudgetConverter.budgetListToDtoList(budgets);

        return new ListResult<>(0, result);
    }
}
