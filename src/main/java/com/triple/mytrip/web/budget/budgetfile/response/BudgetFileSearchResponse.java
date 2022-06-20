package com.triple.mytrip.web.budget.budgetfile.response;

import com.triple.mytrip.domain.budget.budgetfile.BudgetFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BudgetFileSearchResponse {

    private Long id;
    private String oriName;
    private String fileName;

    @Builder
    public BudgetFileSearchResponse(Long id, String oriName, String fileName) {
        this.id = id;
        this.oriName = oriName;
        this.fileName = fileName;
    }

    public static BudgetFileSearchResponse toResponse(BudgetFile budgetFile) {
        return BudgetFileSearchResponse.builder()
                .id(budgetFile.getId())
                .oriName(budgetFile.getOriName())
                .fileName(budgetFile.getFileName())
                .build();
    }
}
