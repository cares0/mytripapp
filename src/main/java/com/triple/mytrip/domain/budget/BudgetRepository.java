package com.triple.mytrip.domain.budget;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    @EntityGraph(attributePaths = "budgetFiles")
    @Query("select b from Budget b where b.id = :id")
    Budget findByIdWithBudgetFiles(@Param("id") Long budgetId);

}
