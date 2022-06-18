package com.triple.mytrip.web.budget;

import com.triple.mytrip.domain.budget.Budget;
import com.triple.mytrip.domain.budget.BudgetService;
import com.triple.mytrip.domain.budget.budgetfile.BudgetFileService;
import com.triple.mytrip.web.budget.response.BudgetSearchResponse;
import com.triple.mytrip.web.budget.response.BudgetEditResponse;
import com.triple.mytrip.web.budget.request.BudgetEditRequest;
import com.triple.mytrip.web.common.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.triple.mytrip.web.budget.BudgetConverter.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BudgetController {

    private final BudgetService budgetService;
    private final BudgetFileService budgetFileService;

    @PostMapping("/budgets/{budgetId}/budget-files")
    public Result<Integer> saveFile(@PathVariable Long budgetId, List<MultipartFile> files) throws IOException {
        int count = budgetFileService.save(budgetId, files);

        return new Result<>(count);
    }

    @GetMapping("/budgets/{budgetId}")
    public Result<BudgetSearchResponse> searchOne(@PathVariable Long budgetId) {
        Budget budget = budgetService.getOne(budgetId);
        BudgetSearchResponse budgetSearchResponse = entityToSearchResponse(budget);
        return new Result<>(budgetSearchResponse);
    }

    @PatchMapping(value = "/budgets/{budgetId}")
    public BudgetEditResponse edit(@PathVariable Long budgetId, @RequestBody BudgetEditRequest budgetEditRequest) {
        Budget modifiedBudget = editRequestToEntity(budgetEditRequest);

        modifiedBudget = budgetService.edit(budgetId, modifiedBudget);

        BudgetEditResponse budgetEditResponse = entityToEditResponse(modifiedBudget);

        return budgetEditResponse;
    }

    @DeleteMapping("/budgets/{budgetId}")
    public Result<String> delete(@PathVariable Long budgetId) {
        budgetService.delete(budgetId);
        return new Result<>("deleted");
    }

}
