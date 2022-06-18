package com.triple.mytrip.domain.checklist.category;

import com.triple.mytrip.domain.exception.EntityNotFoundException;
import com.triple.mytrip.domain.trip.Trip;
import com.triple.mytrip.domain.trip.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChecklistCategoryService {

    private final ChecklistCategoryRepository checklistCategoryRepository;
    private final TripRepository tripRepository;

    @Transactional
    public Long save(Long tripId, ChecklistCategory checklistCategory) {
        Trip trip = findTrip(tripId);

        checklistCategory.addTrip(trip);

        ChecklistCategory saved = checklistCategoryRepository.save(checklistCategory);

        return saved.getId();
    }

    public List<ChecklistCategory> getListWithChecklist(Long tripId) {
        return checklistCategoryRepository.findAllByTripWithChecklist(tripId);
    }

    @Transactional
    public ChecklistCategory editName(Long categoryId, String name) {
        ChecklistCategory category = findCategory(categoryId);
        category.editName(name);
        return category;
    }

    @Transactional
    public void delete(Long categoryId) {
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
