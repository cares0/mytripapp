package com.triple.mytrip.web.budget;

import com.triple.mytrip.domain.budget.Budget;
import com.triple.mytrip.domain.budget.BudgetService;
import com.triple.mytrip.web.budget.response.BudgetSearchResponse;
import com.triple.mytrip.web.budget.response.BudgetEditResponse;
import com.triple.mytrip.web.budget.request.BudgetEditRequest;
import com.triple.mytrip.web.common.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @PostMapping("/budgets/{budgetId}/budget-files")
    public Result<Integer> budgetFileAdd(@PathVariable Long budgetId, List<MultipartFile> files) throws IOException {
        int count = budgetService.addFile(budgetId, files);
        return new Result<>(count);
    }

    @GetMapping("/budgets/{budgetId}")
    public BudgetSearchResponse budgetDetail(@PathVariable Long budgetId) {
        Budget budget = budgetService.getOne(budgetId);
        return BudgetSearchResponse.toResponse(budget);
    }

    @PatchMapping(value = "/budgets/{budgetId}")
    public BudgetEditResponse budgetModify(@PathVariable Long budgetId, @RequestBody BudgetEditRequest budgetEditRequest) {
        Budget modified = budgetEditRequest.toEntity();
        modified = budgetService.modify(budgetId, modified);
        return BudgetEditResponse.toResponse(modified);
    }

    @DeleteMapping("/budgets/{budgetId}")
    public Result<Long> budgetRemove(@PathVariable Long budgetId) {
        budgetService.remove(budgetId);
        return new Result<>(budgetId);
    }

}
