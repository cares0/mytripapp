package com.triple.mytrip.domain.budget.budgetfile;

import com.triple.mytrip.domain.budget.Budget;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class BudgetFile {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
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
