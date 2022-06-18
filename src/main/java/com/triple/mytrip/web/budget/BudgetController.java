package com.triple.mytrip.web.budget;

import com.triple.mytrip.domain.budget.Budget;
import com.triple.mytrip.domain.budget.BudgetService;
import com.triple.mytrip.domain.budget.budgetfile.BudgetFileService;
import com.triple.mytrip.web.budget.response.BudgetSearchResponse;
import com.triple.mytrip.web.budget.response.BudgetEditResponse;
import com.triple.mytrip.web.budget.request.BudgetEditAll;
import com.triple.mytrip.web.budget.request.BudgetEditOrderAndDate;
import com.triple.mytrip.web.budget.request.BudgetEditRequest;
import com.triple.mytrip.web.common.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

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
        BudgetSearchResponse budgetSearchResponse = BudgetConverter.budgetToDto(budget);
        return new Result<>(budgetSearchResponse);
    }

    @PatchMapping(value = "/budgets/{budgetId}")
    public Result<BudgetEditResponse> edit(@PathVariable Long budgetId, @RequestBody BudgetEditRequest budgetEditRequest) {
        Budget modifiedBudget = updateEntity(budgetId, budgetEditRequest);

        BudgetEditResponse budgetEditResponse = BudgetConverter.entityToEditResponse(modifiedBudget);

        return new Result<>(budgetEditResponse);
    }

    @DeleteMapping("/budgets/{budgetId}")
    public Result<String> delete(@PathVariable Long budgetId) {
        budgetService.delete(budgetId);
        return new Result<>("Success");
    }

    private Budget updateEntity(Long budgetId, BudgetEditRequest budgetEditRequest) {
        if (Objects.nonNull(budgetEditRequest.getBudgetEditAll())) {
            BudgetEditAll budgetEditAll = budgetEditRequest.getBudgetEditAll();
            Budget modifiedBudget = new Budget(
                    budgetEditAll.getTripCategory(),
                    budgetEditAll.getPrice(),
                    budgetEditAll.getDate(),
                    budgetEditAll.getPaymentPlan(),
                    budgetEditAll.getOrder(),
                    budgetEditAll.getPlace(),
                    budgetEditAll.getContent());
            return budgetService.editAll(budgetId, modifiedBudget);
        } else {
            BudgetEditOrderAndDate budgetEditOrderAndDate = budgetEditRequest.getBudgetEditOrderAndDate();
            Budget modifiedBudget = new Budget(
                    budgetEditOrderAndDate.getOrder(),
                    budgetEditOrderAndDate.getDate());
            return budgetService.editOrderAndDate(budgetId, modifiedBudget);
        }
    }

}
