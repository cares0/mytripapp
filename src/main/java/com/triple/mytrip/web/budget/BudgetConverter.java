package com.triple.mytrip.web.budget;

import com.triple.mytrip.domain.budget.Budget;
import com.triple.mytrip.web.budget.response.BudgetSearchResponse;
import com.triple.mytrip.web.budget.response.BudgetEditResponse;
import com.triple.mytrip.web.budget.response.BudgetFileSearchResponse;
import com.triple.mytrip.web.budget.request.BudgetSaveRequest;

import java.util.List;
import java.util.stream.Collectors;

// 나중에 인터페이스로 구현 고려, 어댑터 패턴 적용 고려
public class BudgetConverter {

    public static BudgetSearchResponse entityToSearchResponse(Budget budget) {
        return new BudgetSearchResponse(
                budget.getId(),
                budget.getPrice(),
                budget.getPlace(),
                budget.getDate(),
                budget.getOrder(),
                budget.getBudgetFiles().stream().map((budgetFile) -> new BudgetFileSearchResponse(
                        budgetFile.getId(),
                        budgetFile.getOriName(),
                        budgetFile.getFileName())
                ).collect(Collectors.toList()),
                budget.getPaymentPlan(),
                budget.getTripCategory(),
                budget.getContent());
    }

    public static List<BudgetSearchResponse> entityListToSearchResponseList(List<Budget> list) {
        return list.stream().map(
                        (budget) -> entityToSearchResponse(budget))
                .collect(Collectors.toList());
    }

    public static Budget saveRequestToEntity(BudgetSaveRequest budgetSaveRequest) {
        return new Budget(
                budgetSaveRequest.getTripCategory(),
                budgetSaveRequest.getPrice(),
                budgetSaveRequest.getDate(),
                budgetSaveRequest.getPaymentPlan(),
                budgetSaveRequest.getBudgetOrder(),
                budgetSaveRequest.getPlace(),
                budgetSaveRequest.getContent());
    }

    public static BudgetEditResponse entityToEditResponse(Budget budget) {
        return new BudgetEditResponse(
                budget.getId(),
                budget.getTripCategory(),
                budget.getPrice(),
                budget.getDate(),
                budget.getPaymentPlan(),
                budget.getOrder(),
                budget.getPlace(),
                budget.getContent());
    }
}
