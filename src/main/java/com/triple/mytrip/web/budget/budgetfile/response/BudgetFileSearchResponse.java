package com.triple.mytrip.web.budget.budgetfile.response;

import com.triple.mytrip.domain.budget.budgetfile.BudgetFile;
import lombok.*;

import java.util.Objects;

@Getter @Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BudgetFileSearchResponse {

    private Long id;
    private String oriName;
    private String fileName;

    public static BudgetFileSearchResponse toResponse(BudgetFile budgetFile) {
        if (Objects.isNull(budgetFile)) {
            return null;
        }
        return BudgetFileSearchResponse.builder()
                .id(budgetFile.getId())
                .oriName(budgetFile.getOriName())
                .fileName(budgetFile.getFileName())
                .build();
    }
}
