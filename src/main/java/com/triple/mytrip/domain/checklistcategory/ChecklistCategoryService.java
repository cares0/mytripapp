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
    private final TripRepository tripRepository;

    public Long add(Long tripId, ChecklistCategory checklistCategory) {
        Trip trip = findTrip(tripId);
        checklistCategory.addTrip(trip);
        ChecklistCategory saved = checklistCategoryRepository.save(checklistCategory);
        return saved.getId();
    }

    public Long addChecklist(Long categoryId, Checklist checklist) {
        ChecklistCategory category = findCategory(categoryId);
        checklist.addCategory(category);
        return checklistRepository.save(checklist).getId();
    }

    @Transactional(readOnly = true)
    public List<ChecklistCategory> getListWithChecklist(Long tripId) {
        return checklistCategoryRepository.findAllByTripIdWithChecklist(tripId);
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

    private Trip findTrip(Long tripId) {
        return tripRepository.findById(tripId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 여행을 찾을 수 없음"));
    }
}
