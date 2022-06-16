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
@Transactional
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final TripRepository tripRepository;
    private final BudgetFileRepository budgetFileRepository;

    private final FileManager fileManager;


    public Long save(Long tripId, Budget budget) {
        Trip trip = findTrip(tripId);

        budget.addTrip(trip);

        return budgetRepository.save(budget).getId();
    }

    public int saveFile(Long budgetId, List<MultipartFile> multipartFiles) throws IOException {
        Budget budget = findBudget(budgetId);

        List<UploadFile> uploadFiles = fileManager.storeFiles(multipartFiles);
        List<BudgetFile> budgetFiles = uploadFilesToBudgetFiles(budget, uploadFiles);

        saveFile(budgetFiles);

        return budgetFiles.size();
    }

    public Budget getOne(Long id) {
        return findBudget(id);
    }

    public List<Budget> getList(Long tripId) {
        Trip trip = findTrip(tripId);
        return budgetRepository.findAllByTrip(trip);
    }

    public void editAll(Long budgetId, Budget budget) {
        Budget findBudget = getOne(budgetId);

        editBudget(budget, findBudget);
    }

    public void editOrder(Long budgetId, Integer order) {
        Budget findBudget = getOne(budgetId);

        findBudget.editOrder(order);
    }

    public void editDate(Long budgetId, LocalDate date) {
        Budget findBudget = getOne(budgetId);

        findBudget.editDate(date);
    }

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

    private List<BudgetFile> uploadFilesToBudgetFiles(Budget budget, List<UploadFile> uploadFiles) {
        return uploadFiles.stream().map(
                        (uploadFile) -> new BudgetFile(budget, uploadFile.getUploadFileName(), uploadFile.getStoreFileName()))
                .collect(Collectors.toList());
    }

    private void saveFile(List<BudgetFile> budgetFiles) {
        budgetFiles.stream()
                .forEach((budgetFile) -> budgetFileRepository.save(budgetFile));
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
