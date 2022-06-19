package com.triple.mytrip.domain.schedule;

import com.triple.mytrip.domain.exception.EntityNotFoundException;
import com.triple.mytrip.domain.place.Place;
import com.triple.mytrip.domain.place.PlaceRepository;
import com.triple.mytrip.domain.trip.Trip;
import com.triple.mytrip.domain.trip.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
