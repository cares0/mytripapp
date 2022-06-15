package com.triple.mytrip.domain.budget.budgetfile;

import com.triple.mytrip.domain.budget.Budget;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BudgetFile {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budget_id")
    private Budget budget;

    private String oriName;

    private String fileName;

    public BudgetFile(Budget budget, String oriName, String fileName) {
        addBudget(budget);
        this.oriName = oriName;
        this.fileName = fileName;
    }

    private void addBudget(Budget budget) {
        this.budget = budget;
        budget.getBudgetFiles().add(this);
    }

}
