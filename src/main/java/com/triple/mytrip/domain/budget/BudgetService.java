package com.triple.mytrip.domain.budget;

import com.triple.mytrip.domain.exception.EntityNotFoundException;
import com.triple.mytrip.domain.trip.Trip;
import com.triple.mytrip.domain.trip.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final TripRepository tripRepository;

    public Long save(Budget budget, Long tripId) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 여행을 찾을 수 없음"));

        budget.addTrip(trip);

        Budget saved = budgetRepository.save(budget);
        return saved.getId();
    }

    public Budget getOne(Long id) {
        return budgetRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 가계부가 없음"));
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

    public void delete(Long id) {
        budgetRepository.deleteById(id);
    }



}
