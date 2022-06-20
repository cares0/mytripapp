package com.triple.mytrip.web.budget;

import com.triple.mytrip.domain.budget.Budget;
import com.triple.mytrip.domain.budget.BudgetService;
import com.triple.mytrip.domain.budget.budgetfile.BudgetFileService;
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
    private final BudgetFileService budgetFileService;

    @PostMapping("/budgets/{budgetId}/budget-files")
    public Result<Integer> registerFile(@PathVariable Long budgetId, List<MultipartFile> files) throws IOException {
        int count = budgetFileService.store(budgetId, files);
        return new Result<>(count);
    }

    @GetMapping("/budgets/{budgetId}")
    public BudgetSearchResponse searchOne(@PathVariable Long budgetId) {
        Budget budget = budgetService.getOne(budgetId);
        return BudgetSearchResponse.toResponse(budget);
    }

    @PatchMapping(value = "/budgets/{budgetId}")
    public BudgetEditResponse update(@PathVariable Long budgetId, @RequestBody BudgetEditRequest budgetEditRequest) {
        Budget modified = budgetEditRequest.toEntity();
        modified = budgetService.edit(budgetId, modified);
        return BudgetEditResponse.toResponse(modified);
    }

    @DeleteMapping("/budgets/{budgetId}")
    public Result<Long> delete(@PathVariable Long budgetId) {
        budgetService.delete(budgetId);
        return new Result<>(budgetId);
    }

}
