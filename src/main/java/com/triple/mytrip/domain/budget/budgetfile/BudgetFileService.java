package com.triple.mytrip.domain.budget.budgetfile;

import com.triple.mytrip.domain.budget.Budget;
import com.triple.mytrip.domain.budget.BudgetRepository;
import com.triple.mytrip.domain.exception.EntityNotFoundException;
import com.triple.mytrip.domain.util.FileManager;
import com.triple.mytrip.domain.util.UploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BudgetFileService {

    private final BudgetFileRepository budgetFileRepository;
    private final BudgetRepository budgetRepository;
    private final FileManager fileManager;

    public int store(Long budgetId, List<MultipartFile> multipartFiles) throws IOException {
        Budget budget = findBudget(budgetId);

        List<UploadFile> uploadFiles = fileManager.storeFiles(multipartFiles);
        List<BudgetFile> budgetFiles = uploadFilesToBudgetFiles(budget, uploadFiles);

        saveFile(budgetFiles);

        return budgetFiles.size();
    }

    public void delete(Long id) {
        BudgetFile budgetFile = budgetFileRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 파일을 찾을 수 없음"));

        budgetFileRepository.deleteById(id);
        fileManager.deleteFile(budgetFile.getFileName());
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

    private Budget findBudget(Long id) {
        return budgetRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 가계부를 찾을 수 없음"));
    }
}
