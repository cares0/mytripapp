package com.triple.mytrip.web.budget.budgetfile;

import com.triple.mytrip.domain.budget.budgetfile.BudgetFileService;
import com.triple.mytrip.web.common.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/budget-files")
public class BudgetFileController {

    private final BudgetFileService budgetFileService;

    @DeleteMapping("/{budgetFileId}")
    public Result<Long> delete(@PathVariable Long budgetFileId) {
        budgetFileService.remove(budgetFileId);
        return new Result<>(budgetFileId);
    }
}
