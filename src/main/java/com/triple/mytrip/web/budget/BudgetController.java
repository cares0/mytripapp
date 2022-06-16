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

/*    @PatchMapping("/budgets/{budgetId}")
    public Result edit(@PathVariable Long budgetId, @RequestBody BudgetEditForm) {


    }*/

    @DeleteMapping("/budgets/{budgetId}")
    public Result<String> delete(@PathVariable Long budgetId) {
        budgetService.delete(budgetId);
        return new Result<>("Success");
    }




}
