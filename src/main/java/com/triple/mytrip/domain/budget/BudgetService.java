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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final TripRepository tripRepository;
    private final BudgetFileRepository budgetFileRepository;

    private final FileManager fileManager;


    public Long save(Budget budget, Long tripId) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 여행을 찾을 수 없음"));

        budget.addTrip(trip);

        Budget saved = budgetRepository.save(budget);
        return saved.getId();
    }

    public int saveFile(Long budgetId, List<MultipartFile> multipartFiles) throws IOException {
        Budget budget = budgetRepository.findById(budgetId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 가계부가 없음"));

        List<UploadFile> uploadFiles = fileManager.storeFiles(multipartFiles);

        List<BudgetFile> budgetFiles = uploadFiles.stream().map(
                        (uploadFile) -> new BudgetFile(budget, uploadFile.getUploadFileName(), uploadFile.getStoreFileName()))
                .collect(Collectors.toList());

        budgetFiles.stream()
                .forEach((budgetFile) -> budgetFileRepository.save(budgetFile));

        return budgetFiles.size();
    }

    public Budget getOne(Long id) {
        return budgetRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 가계부가 없음"));
    }

    public List<Budget> getList(Long tripId) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 여행을 찾을 수 없음"));
        return budgetRepository.findAllByTrip(trip);
    }

    public void editAll(Budget budget, Long budgetId) {
        Budget findBudget = getOne(budgetId);

        findBudget.editAll(budget.getTripCategory(), budget.getPrice(),
                budget.getDate(), budget.getPaymentPlan(),
                budget.getPlace(), budget.getContent());
    }

    public void delete(Long id) {
        budgetRepository.deleteById(id);
    }



}
