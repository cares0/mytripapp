package com.triple.mytrip.domain.schedule;

import com.triple.mytrip.domain.exception.EntityNotFoundException;
import com.triple.mytrip.domain.place.Place;
import com.triple.mytrip.domain.place.PlaceRepository;
import com.triple.mytrip.domain.trip.Trip;
import com.triple.mytrip.domain.trip.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final TripRepository tripRepository;
    private final ScheduleRepository scheduleRepository;
    private final PlaceRepository placeRepository;

    @Transactional
    public Long save(Long tripId, Long placeId, Schedule schedule) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 여행을 찾을 수 없음"));

        Place place = placeRepository.findById(placeId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 장소를 찾을 수 없음"));

        schedule.addTrip(trip);
        schedule.addPlace(place);
        Schedule saved = scheduleRepository.save(schedule);

        return saved.getId();
    }

    @Transactional
    public Schedule edit(Long id, Schedule modified) {
        Schedule original = scheduleRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 일정을 찾을 수 없음"));

        update(original, modified);

        return original;
    }

    @Transactional
    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }

    private void update(Schedule original, Schedule modified) {
        LocalTime visitTime = modified.getVisitTime();
        if (Objects.nonNull(visitTime)) {
            original.editVisitTime(visitTime);
        }
        String memo = modified.getMemo();
        if (Objects.nonNull(memo)) {
            original.editMemo(memo);
        }
        LocalDate date = modified.getDate();
        if (Objects.nonNull(date)) {
            original.editDate(date);
        }
        Integer visitOrder = modified.getVisitOrder();
        if (Objects.nonNull(visitOrder)) {
            original.editVisitOrder(visitOrder);
        }
        Integer arrangeOrder = modified.getArrangeOrder();
        if (Objects.nonNull(arrangeOrder)) {
            original.editArrangeOrder(arrangeOrder);
        }
    }

}
