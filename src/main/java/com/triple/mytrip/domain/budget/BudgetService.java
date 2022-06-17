package com.triple.mytrip.domain.budget;

import com.triple.mytrip.domain.budget.budgetfile.BudgetFile;
import com.triple.mytrip.domain.budget.budgetfile.BudgetFileRepository;
import com.triple.mytrip.domain.exception.EntityNotFoundException;
import com.triple.mytrip.domain.trip.Trip;
import com.triple.mytrip.domain.trip.TripRepository;
import com.triple.mytrip.domain.util.FileManager;
import com.triple.mytrip.domain.util.UploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public void editAll(Long budgetId, Budget budget) {
        Budget findBudget = findBudget(budgetId);

        editBudget(budget, findBudget);
    }

    @Transactional
    public void editOrder(Long budgetId, Integer order) {
        Budget findBudget = findBudget(budgetId);

        findBudget.editOrder(order);
    }

    @Transactional
    public void editDate(Long budgetId, LocalDate date) {
        Budget findBudget = findBudget(budgetId);

        findBudget.editDate(date);
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

    private void editBudget(Budget budget, Budget findBudget) {
        findBudget.editAll(
                budget.getTripCategory(),
                budget.getPrice(),
                budget.getDate(),
                budget.getPaymentPlan(),
                budget.getPlace(),
                budget.getContent());
    }



}
