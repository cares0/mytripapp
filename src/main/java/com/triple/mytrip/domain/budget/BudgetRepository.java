package com.triple.mytrip.domain.budget;

import com.triple.mytrip.domain.trip.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    List<Budget> findAllByTrip(Trip trip);

    @Query("select distinct b from Budget b join fetch b.budgetFiles where b.id = :id")
    Budget findByIdWithBudgetFiles(@Param("id") Long budgetId);

}
