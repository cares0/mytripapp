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
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BudgetController {

    private final BudgetService budgetService;

    @PostMapping("/budgets/{budgetId}/budget-files")
    public Result<Integer> saveFile(@PathVariable Long budgetId, List<MultipartFile> files) throws IOException {
        int count = budgetService.saveFile(budgetId, files);

        return new Result<>(count);
    }

    @GetMapping("/budgets/{budgetId}")
    public BudgetDto searchOne(@PathVariable Long budgetId) {
        Budget budget = budgetService.getOne(budgetId);
        return BudgetConverter.budgetToDto(budget);
    }

    @PatchMapping("/budgets/{budgetId}")
    public Result<String> edit(@PathVariable Long budgetId, @RequestBody BudgetForm budgetForm) {
        Budget budget = BudgetConverter.formToBudget(budgetForm);
        budgetService.editAll(budgetId, budget);
        return new Result<>("Success");
    }

    @PatchMapping("/budgets/{budgetId}/edit-order")
    public Result<String> editOrder(@PathVariable Long budgetId, @RequestBody Integer order) {
        budgetService.editOrder(budgetId, order);
        return new Result<>("Success");
    }

    @PatchMapping("/budgets/{budgetId}/edit-date")
    public Result<String> editDate(@PathVariable Long budgetId, @RequestBody LocalDate date) {
        log.info("Date = {}", date);
        budgetService.editDate(budgetId, date);
        return new Result<>("Success");
    }

    @DeleteMapping("/budgets/{budgetId}")
    public Result<String> delete(@PathVariable Long budgetId) {
        budgetService.delete(budgetId);
        return new Result<>("Success");
    }

}
