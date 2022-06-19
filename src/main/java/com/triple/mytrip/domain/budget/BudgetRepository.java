package com.triple.mytrip.domain.budget;

import com.triple.mytrip.domain.trip.Trip;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    List<Budget> findAllByTrip(Trip trip);

    @EntityGraph(attributePaths = "budgetFiles")
    @Query("select distinct b from Budget b where b.id = :id order by b.date asc, b.order asc")
    Budget findByIdWithBudgetFiles(@Param("id") Long budgetId);

    //join fetch b.budgetFiles

}
