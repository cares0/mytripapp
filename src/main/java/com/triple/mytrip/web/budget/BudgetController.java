package com.triple.mytrip.web.budget;

import com.triple.mytrip.domain.budget.Budget;
import com.triple.mytrip.domain.budget.BudgetService;
import com.triple.mytrip.web.budget.response.BudgetSearchResponse;
import com.triple.mytrip.web.budget.response.BudgetEditResponse;
import com.triple.mytrip.web.budget.request.BudgetEditRequest;
import com.triple.mytrip.web.common.Result;
import com.triple.mytrip.web.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;
    private final ValidationUtils validationUtils;

    @PostMapping("/budgets/{budgetId}/budget-files")
    public Result<List<Long>> budgetFileAdd(@PathVariable Long budgetId,
                                            @RequestParam List<MultipartFile> files) throws IOException {
        List<Long> savedIds = budgetService.addFile(budgetId, files);
        return new Result<>(savedIds);
    }

    @GetMapping("/budgets/{budgetId}")
    public BudgetSearchResponse budgetDetail(@PathVariable Long budgetId) {
        Budget budget = budgetService.getOneWithBudgetFile(budgetId);
        return BudgetSearchResponse.toResponse(budget);
    }

    @PatchMapping(value = "/budgets/{budgetId}")
    public BudgetEditResponse budgetModify(@PathVariable Long budgetId,
                                           @Validated @RequestBody BudgetEditRequest budgetEditRequest,
                                           BindingResult bindingResult) {
        validationUtils.validate(bindingResult);
        
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
