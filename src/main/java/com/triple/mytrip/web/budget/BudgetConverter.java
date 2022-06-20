package com.triple.mytrip.web.budget;

import com.triple.mytrip.domain.budget.Budget;
import com.triple.mytrip.web.budget.request.BudgetEditRequest;
import com.triple.mytrip.web.budget.response.BudgetSearchResponse;
import com.triple.mytrip.web.budget.response.BudgetEditResponse;
import com.triple.mytrip.web.budget.budgetfile.response.BudgetFileSearchResponse;
import com.triple.mytrip.web.budget.request.BudgetSaveRequest;

import java.util.List;
import java.util.stream.Collectors;

// 나중에 인터페이스로 구현 고려, 어댑터 패턴 적용 고려
public class BudgetConverter {

    public static BudgetSearchResponse entityToSearchResponse(Budget budget) {
        return null;
    }

    // budgetFile은 응답할 필요가 없기에, 기본 Response 객체를 만드는 private메서드를 만들어서 재사용(단건 조회할 때는 기본 Request객체 + File이 필요함)
    public static List<BudgetSearchResponse> entityListToSearchResponseList(List<Budget> list) {
        return list.stream().map(
                        (budget) -> createBudgetSearchResponse(budget))
                .collect(Collectors.toList());
    }

    public static Budget saveRequestToEntity(BudgetSaveRequest budgetSaveRequest) {
        return null;
    }

    public static Budget editRequestToEntity(BudgetEditRequest budgetEditRequest) {
        return null;
    }

    public static BudgetEditResponse entityToEditResponse(Budget budget) {
        return null;
    }

    private static BudgetSearchResponse createBudgetSearchResponse(Budget budget) {
        return null;
    }
}
