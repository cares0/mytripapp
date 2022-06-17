package com.triple.mytrip.domain.place;

import com.triple.mytrip.domain.exception.EntityNotFoundException;
import com.triple.mytrip.domain.trip.Trip;
import com.triple.mytrip.domain.trip.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

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

    public void editVisitTime(Long placeId, LocalTime visitTime) {
        Place place = placeRepository.findById(placeId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 장소가 없음"));

        place.editVisitTime(visitTime);
    }

    public void editMemo(Long placeId, String memo) {
        Place place = placeRepository.findById(placeId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 장소가 없음"));

        place.editMemo(memo);
    }

    public void editDate(Long placeId, LocalDate date) {
        Place place = placeRepository.findById(placeId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 장소가 없음"));

        place.editDate(date);
    }

    public void editOrder(Long placeId, Integer order) {
        Place place = placeRepository.findById(placeId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 장소가 없음"));

        place.editPlaceOrder(order);
    }

}
