package com.triple.mytrip.web.budget;

import com.triple.mytrip.domain.budget.Budget;
import com.triple.mytrip.domain.budget.BudgetService;
import com.triple.mytrip.web.budget.dto.BudgetDto;
import com.triple.mytrip.web.budget.form.BudgetForm;
import com.triple.mytrip.web.common.ListResult;
import com.triple.mytrip.web.common.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BudgetController {

    private final BudgetService budgetService;

    @PostMapping("/trip/{tripId}/budgets")
    public Result<Long> save(@PathVariable Long tripId, @RequestBody BudgetForm budgetForm) {
        Budget budget = formToBudget(budgetForm);
        Long save = budgetService.save(budget, tripId);
        return new Result<>(save);
    }

    @PostMapping("/budgets/{budgetId}/budget-files")
    public Result<Integer> saveFile(@PathVariable Long budgetId, List<MultipartFile> files) throws IOException {
        int count = budgetService.saveFile(budgetId, files);

        return new Result<>(count);
    }

    @GetMapping("/trip/{tripId}/budgets")
    public ListResult<BudgetDto> searchList(@PathVariable Long tripId) {
        List<Budget> budgets = budgetService.getList(tripId);

        List<BudgetDto> result = budgetListToDtoList(budgets);

        return new ListResult<>(0, result);
    }

    @GetMapping("/budgets/{budgetId}")
    public BudgetDto searchOne(@PathVariable Long budgetId) {
        Budget budget = budgetService.getOne(budgetId);
        return budgetToDto(budget);
    }

/*    @PatchMapping("/budgets/{budgetId}")
    public Result edit(@PathVariable Long budgetId, @RequestBody BudgetEditForm) {


    }*/

    @DeleteMapping("/budgets/{budgetId}")
    public Result<String> delete(@PathVariable Long budgetId) {
        budgetService.delete(budgetId);
        return new Result<>("Success");
    }

    private BudgetDto budgetToDto(Budget budget) {

        return new BudgetDto(budget.getId(),
                budget.getPrice(),
                budget.getPlace(),
                budget.getDate(),
                budget.getBudgetOrder(),
                budget.getTripCategory(),
                budget.getPaymentPlan(),
                budget.getContent());
    }

    private List<BudgetDto> budgetListToDtoList(List<Budget> list) {
        return list.stream().map(
                        (budget) -> budgetToDto(budget))
                .collect(Collectors.toList());
    }

    private Budget formToBudget(BudgetForm budgetSaveForm) {
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
