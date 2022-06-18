package com.triple.mytrip.domain.checklist;

import com.triple.mytrip.domain.checklist.category.ChecklistCategory;
import com.triple.mytrip.domain.checklist.category.ChecklistCategoryRepository;
import com.triple.mytrip.domain.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Checklist editName(Long checklistId, String name) {
        Checklist checklist = findChecklist(checklistId);
        checklist.editName(name);
        return checklist;
    }

    @Transactional
    public Checklist editCheckStatus(Long checklistId, Boolean checkStatus) {
        Checklist checklist = findChecklist(checklistId);
        checklist.editCheckStatus(checkStatus);
        return checklist;
    }

    @Transactional
    public Checklist editMemo(Long checklistId, String memo) {
        Checklist checklist = findChecklist(checklistId);
        checklist.editMemo(memo);
        return checklist;
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

}
