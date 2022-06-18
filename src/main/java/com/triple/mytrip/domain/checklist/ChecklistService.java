package com.triple.mytrip.domain.checklist;

import com.triple.mytrip.domain.checklist.category.ChecklistCategory;
import com.triple.mytrip.domain.checklist.category.ChecklistCategoryRepository;
import com.triple.mytrip.domain.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChecklistService {

    private final ChecklistRepository checklistRepository;
    private final ChecklistCategoryRepository categoryRepository;

    @Transactional
    public Long save(Long categoryId, Checklist checklist) {
        ChecklistCategory category = findCategory(categoryId);

        checklist.addCategory(category);

        return checklistRepository.save(checklist).getId();
    }

    public Checklist getOne(Long checklistId) {
        return findChecklist(checklistId);
    }

    @Transactional
    public Checklist edit(Long checklistId, Checklist modifiedChecklist) {
        Checklist original = findChecklist(checklistId);

        update(original, modifiedChecklist);

        return original;
    }
    @Transactional
    public void delete(Long checklistId) {
        checklistRepository.deleteById(checklistId);
    }

    private Checklist findChecklist(Long checklistId) {
        return checklistRepository.findById(checklistId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 체크리스트를 찾을 수 없음"));
    }

    private ChecklistCategory findCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 체크리스트 카테고리를 찾을 수 없음"));
    }

    private void update(Checklist original, Checklist modifiedChecklist) {
        String name = modifiedChecklist.getName();
        if (Objects.nonNull(name)) {
            original.editName(name);
        }
        String memo = modifiedChecklist.getMemo();
        if (Objects.nonNull(memo)) {
            original.editMemo(memo);
        }
        Boolean checkStatus = modifiedChecklist.getCheckStatus();
        if (Objects.nonNull(checkStatus)) {
            original.editCheckStatus(checkStatus);
        }
    }
}
