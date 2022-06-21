package com.triple.mytrip.domain.checklistcategory.checklist;

import com.triple.mytrip.domain.checklistcategory.ChecklistCategory;
import com.triple.mytrip.domain.checklistcategory.ChecklistCategoryRepository;
import com.triple.mytrip.domain.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class ChecklistService {

    private final ChecklistRepository checklistRepository;

    @Transactional(readOnly = true)
    public Checklist getOne(Long checklistId) {
        return findChecklist(checklistId);
    }

    public Checklist modify(Long checklistId, Checklist modified) {
        Checklist original = findChecklist(checklistId);
        update(original, modified);
        return original;
    }

    public void remove(Long checklistId) {
        checklistRepository.deleteById(checklistId);
    }

    private Checklist findChecklist(Long checklistId) {
        return checklistRepository.findById(checklistId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 체크리스트를 찾을 수 없음"));
    }

    private void update(Checklist original, Checklist modified) {
        String name = modified.getName();
        if (Objects.nonNull(name)) {
            original.editName(name);
        }
        String memo = modified.getMemo();
        if (Objects.nonNull(memo)) {
            original.editMemo(memo);
        }
        Boolean checkStatus = modified.getCheckStatus();
        if (Objects.nonNull(checkStatus)) {
            original.editCheckStatus(checkStatus);
        }
    }
}
