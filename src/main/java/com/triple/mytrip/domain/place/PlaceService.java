package com.triple.mytrip.domain.place;

import com.triple.mytrip.domain.exception.EntityNotFoundException;
import com.triple.mytrip.domain.trip.Trip;
import com.triple.mytrip.domain.trip.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaceService {

    private final TripRepository tripRepository;
    private final PlaceRepository placeRepository;

    public Long save(Long tripId, Place place) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 여행을 찾을 수 없음"));
        place.addTrip(trip);
        Place saved = placeRepository.save(place);
        return saved.getId();
    }


}
