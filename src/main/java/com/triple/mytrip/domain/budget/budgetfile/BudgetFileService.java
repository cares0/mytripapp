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

    private final FileManager fileManager;

    public void remove(Long budgetFileId) {
        BudgetFile budgetFile = findBudgetFile(budgetFileId);
        budgetFileRepository.delete(budgetFile);

        fileManager.removeFile(budgetFile.getFileName());
    }

    private BudgetFile findBudgetFile(Long budgetFileId) {
        return budgetFileRepository.findById(budgetFileId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 파일을 찾을 수 없음"));
    }
}
