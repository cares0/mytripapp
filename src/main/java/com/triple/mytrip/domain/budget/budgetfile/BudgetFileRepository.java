package com.triple.mytrip.domain.budget.budgetfile;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetFileRepository extends JpaRepository<BudgetFile, Long> {

    List<BudgetFile> findAllByBudgetId(Long budgetId);
}
