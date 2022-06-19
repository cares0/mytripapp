package com.triple.mytrip.domain.budget;

import com.triple.mytrip.domain.trip.Trip;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    @Query("select b from Budget b where b.trip.id = :id order by b.date asc, b.order asc")
    List<Budget> findAllByTripId(@Param("id") Long tripId);

    @EntityGraph(attributePaths = "budgetFiles")
    @Query("select b from Budget b where b.id = :id")
    Budget findByIdWithBudgetFiles(@Param("id") Long budgetId);

}
