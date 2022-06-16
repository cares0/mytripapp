package com.triple.mytrip.domain.budget.budgetfile;

import com.triple.mytrip.domain.budget.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetFileRepository extends JpaRepository<BudgetFile, Long> {

    List<BudgetFile> findAllByBudget(Budget budget);
}
