package com.triple.mytrip.domain.budget;

import com.triple.mytrip.domain.trip.Trip;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetRepository budgetRepository;

    public Long add(Budget budget) {
        Budget saved = budgetRepository.save(budget);
        return saved.getId();
    }

    public Budget getOne(Long id) {
        return budgetRepository.findById(id).get();
    }

    public List<Budget> getList(Trip trip) {
        return budgetRepository.findAllByTrip(trip);
    }

    public void editAll(Budget budget) {
        Budget findBudget = getOne(budget.getId());

        findBudget.editAll(budget.getTripCategory(), budget.getPrice(),
                budget.getDate(), budget.getPaymentPlan(),
                budget.getPlace(), budget.getContent());
    }



}
