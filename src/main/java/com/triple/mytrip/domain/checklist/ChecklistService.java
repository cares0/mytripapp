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

    public Long save(Long categoryId, Checklist checklist) {
        ChecklistCategory category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 체크리스트 카테고리를 찾을 수 없음"));

        checklist.addCategory(category);

        return checklistRepository.save(checklist).getId();
    }
}
