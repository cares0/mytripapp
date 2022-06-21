package com.triple.mytrip.domain.budget;

import com.triple.mytrip.domain.budget.budgetfile.BudgetFile;
import com.triple.mytrip.domain.exception.EntityNotFoundException;
import com.triple.mytrip.domain.trip.Trip;
import com.triple.mytrip.domain.trip.TripRepository;
import com.triple.mytrip.domain.util.FileManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BudgetService {

    private final TripRepository tripRepository;
    private final BudgetRepository budgetRepository;

    private final FileManager fileManager;

    public Long add(Long tripId, Budget budget) {
        Trip trip = findTrip(tripId);
        budget.addTrip(trip);
        return budgetRepository.save(budget).getId();
    }

    @Transactional(readOnly = true)
    public Budget getOne(Long budgetId) {
        return findBudgetWithBudgetFiles(budgetId);
    }

    @Transactional(readOnly = true)
    public List<Budget> getList(Long tripId) {
        return budgetRepository.findAllByTripId(tripId);
    }

    public Budget modify(Long budgetId, Budget modified) {
        Budget original = findBudget(budgetId);
        update(original, modified);
        return original;
    }

    public void remove(Long budgetId) {
        Budget budget = findBudgetWithBudgetFiles(budgetId);

        // 디스크에 저장된 파일 삭제
        removeFile(budget.getBudgetFiles());

        // DB데이터는 CASCADE로 같이 삭제됨
        budgetRepository.delete(budget);
    }

    private Budget findBudget(Long id) {
        return budgetRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 가계부를 찾을 수 없음"));
    }

    private Budget findBudgetWithBudgetFiles(Long id) {
        return Optional.ofNullable(budgetRepository.findByIdWithBudgetFiles(id)).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 가계부를 찾을 수 없음"));
    }

    private Trip findTrip(Long tripId) {
        return tripRepository.findById(tripId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 여행을 찾을 수 없음"));
    }

    private void removeFile(List<BudgetFile> budgetFiles) {
        budgetFiles.stream().forEach((budgetFile) ->
                fileManager.removeFile(budgetFile.getFileName()));
    }

    private void update(Budget original, Budget modified) {
        BudgetCategory budgetCategory = modified.getBudgetCategory();
        if (Objects.nonNull(budgetCategory)) {
            original.editBudgetCategory(budgetCategory);
        }
        Integer price = modified.getPrice();
        if (Objects.nonNull(price)) {
            original.editPrice(price);
        }
        LocalDate date = modified.getDate();
        if (Objects.nonNull(date)) {
            original.editDate(date);
        }
        PaymentPlan paymentPlan = modified.getPaymentPlan();
        if (Objects.nonNull(paymentPlan)) {
            original.editPaymentPlan(paymentPlan);
        }
        Integer order = modified.getOrder();
        if (Objects.nonNull(order)) {
            original.editOrder(order);
        }
        String place = modified.getPlace();
        if (Objects.nonNull(place)) {
            original.editPlace(place);
        }
        String content = modified.getContent();
        if (Objects.nonNull(content)) {
            original.editContent(content);
        }
    }

}
