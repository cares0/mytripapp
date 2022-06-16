package com.triple.mytrip.domain.budget.budgetfile;

import com.triple.mytrip.domain.exception.EntityNotFoundException;
import com.triple.mytrip.domain.util.FileManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BudgetFileService {

    private final BudgetFileRepository budgetFileRepository;
    private final FileManager fileManager;

    public void delete(Long id) {
        BudgetFile budgetFile = budgetFileRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 파일을 찾을 수 없음"));

        budgetFileRepository.deleteById(id);
        fileManager.deleteFile(budgetFile.getFileName());
    }
}
