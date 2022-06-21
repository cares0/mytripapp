package com.triple.mytrip.domain.checklistcategory;

import com.triple.mytrip.domain.checklistcategory.checklist.Checklist;
import com.triple.mytrip.domain.checklistcategory.checklist.ChecklistRepository;
import com.triple.mytrip.domain.exception.EntityNotFoundException;
import com.triple.mytrip.domain.trip.Trip;
import com.triple.mytrip.domain.trip.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChecklistCategoryService {

    private final ChecklistCategoryRepository checklistCategoryRepository;
    private final ChecklistRepository checklistRepository;

    public Long addChecklist(Long categoryId, Checklist checklist) {
        ChecklistCategory category = findCategory(categoryId);
        checklist.addCategory(category);
        return checklistRepository.save(checklist).getId();
    }

    public ChecklistCategory modify(Long categoryId, ChecklistCategory modified) {
        ChecklistCategory original = findCategory(categoryId);
        original.editName(modified.getName());
        return original;
    }

    public void remove(Long categoryId) {
        checklistCategoryRepository.deleteById(categoryId);
    }

    private ChecklistCategory findCategory(Long categoryId) {
        return checklistCategoryRepository.findById(categoryId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 체크리스트 카테고리를 찾을 수 없음"));
    }
}
