package com.triple.mytrip.domain.budget;

import com.triple.mytrip.domain.budget.budgetfile.BudgetFile;
import com.triple.mytrip.domain.budget.budgetfile.BudgetFileRepository;
import com.triple.mytrip.domain.exception.EntityNotFoundException;
import com.triple.mytrip.domain.util.FileManager;
import com.triple.mytrip.domain.util.UploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final BudgetFileRepository budgetFileRepository;

    private final FileManager fileManager;

    public List<Long> addFile(Long budgetId, List<MultipartFile> multipartFiles) throws IOException {
        Budget budget = findBudget(budgetId);

        List<UploadFile> uploadFiles = fileManager.storeFiles(multipartFiles);
        List<BudgetFile> budgetFiles = uploadFilesToBudgetFiles(uploadFiles);
        addBudgetInBudgetFile(budget, budgetFiles);

        saveFile(budgetFiles);

        return makeIdList(budgetFiles);
    }

    @Transactional(readOnly = true)
    public Budget getOneWithBudgetFile(Long budgetId) {
        return findBudgetWithBudgetFiles(budgetId);
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

    private Budget findBudget(Long budgetId) {
        return budgetRepository.findById(budgetId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 가계부를 찾을 수 없음"));
    }

    private List<BudgetFile> uploadFilesToBudgetFiles(List<UploadFile> uploadFiles) {
        return uploadFiles.stream().map(
                        (uploadFile) -> BudgetFile.builder()
                                .oriName(uploadFile.getUploadFileName())
                                .fileName(uploadFile.getStoreFileName()).build())
                .collect(Collectors.toList());
    }

    private void addBudgetInBudgetFile(Budget budget, List<BudgetFile> budgetFiles) {
        budgetFiles.stream().forEach((budgetFile -> budgetFile.addBudget(budget)));
    }

    private void saveFile(List<BudgetFile> budgetFiles) {
        budgetFiles.stream()
                .forEach((budgetFile) -> budgetFileRepository.save(budgetFile));
    }

    private List<Long> makeIdList(List<BudgetFile> budgetFiles) {
        return budgetFiles.stream().map((budgetFile ->
                budgetFile.getId())).collect(Collectors.toList());
    }

    private Budget findBudgetWithBudgetFiles(Long budgetFileId) {
        return Optional.ofNullable(budgetRepository.findByIdWithBudgetFiles(budgetFileId)).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 가계부를 찾을 수 없음"));
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
