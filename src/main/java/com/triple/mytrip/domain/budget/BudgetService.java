package com.triple.mytrip.domain.budget;

import com.triple.mytrip.domain.budget.budgetfile.BudgetFile;
import com.triple.mytrip.domain.budget.budgetfile.BudgetFileRepository;
import com.triple.mytrip.domain.common.TripCategory;
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

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BudgetService {

    private final TripRepository tripRepository;
    private final BudgetRepository budgetRepository;
    private final BudgetFileRepository budgetFileRepository;

    private final FileManager fileManager;

    @Transactional
    public Long save(Long tripId, Budget budget) {
        Trip trip = findTrip(tripId);

        budget.addTrip(trip);

        return budgetRepository.save(budget).getId();
    }

    public Budget getOne(Long id) {
        return budgetRepository.findByIdWithBudgetFiles(id);
    }

    public List<Budget> getList(Long tripId) {
        Trip trip = findTrip(tripId);
        return budgetRepository.findAllByTrip(trip);
    }

    @Transactional
    public Budget edit(Long budgetId, Budget modified) {
        Budget original = findBudget(budgetId);

        update(original, modified);

        return original;
    }

    @Transactional
    public void delete(Long id) {
        Budget budget = findBudget(id);

        List<BudgetFile> budgetFiles = budgetFileRepository.findAllByBudget(budget);

        // 디스크에 저장된 파일 삭제
        deleteFile(budgetFiles);

        // CASCADE로 같이 삭제됨
        budgetRepository.deleteById(id);
    }

    private Budget findBudget(Long id) {
        return budgetRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 가계부를 찾을 수 없음"));
    }

    private Trip findTrip(Long tripId) {
        return tripRepository.findById(tripId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 여행을 찾을 수 없음"));
    }

    private void deleteFile(List<BudgetFile> budgetFiles) {
        budgetFiles.stream().forEach((budgetFile) ->
                fileManager.deleteFile(budgetFile.getFileName()));
    }

    private void update(Budget original, Budget modified) {
        TripCategory tripCategory = modified.getTripCategory();
        if (Objects.nonNull(tripCategory)) {
            original.editTripCategory(tripCategory);
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
