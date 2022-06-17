package com.triple.mytrip.domain.place;

import com.triple.mytrip.domain.exception.EntityNotFoundException;
import com.triple.mytrip.domain.place.accommodation.Accommodation;
import com.triple.mytrip.domain.place.accommodation.AccommodationRepository;
import com.triple.mytrip.domain.place.flight.Flight;
import com.triple.mytrip.domain.place.flight.FlightRepository;
import com.triple.mytrip.domain.place.restaurant.Restaurant;
import com.triple.mytrip.domain.place.restaurant.RestaurantRepository;
import com.triple.mytrip.domain.place.shop.Shop;
import com.triple.mytrip.domain.place.shop.ShopRepository;
import com.triple.mytrip.domain.place.tour.Tour;
import com.triple.mytrip.domain.place.tour.TourRepository;
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

    @Transactional
    public Long save(Long tripId, Place place) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 여행을 찾을 수 없음"));
        place.addTrip(trip);
        Place saved = placeRepository.save(place);
        return saved.getId();
    }

    // 조회를 우선 Place 테이블에서만 조회, (그냥 placeRepository에서 조회를 해버리면 5개 테이블 모두 조인해버림)
    // 그 다음에 각각의 테이블에 맞춰서 데이터만 조회, -> 5개 테이블 조인을 쿼리 2번으로 하는 방법이 없을까..
    public Place getOne(Long placeId) {
        Place place = placeRepository.findById(placeId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 장소가 없음"));
        return place;
    }

    @Transactional
    public void editVisitTime(Long placeId, LocalTime visitTime) {
        Place place = placeRepository.findById(placeId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 장소가 없음"));

        place.editVisitTime(visitTime);
    }

    @Transactional
    public void editMemo(Long placeId, String memo) {
        Place place = placeRepository.findById(placeId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 장소가 없음"));

        place.editMemo(memo);
    }

    @Transactional
    public void editDate(Long placeId, LocalDate date) {
        Place place = placeRepository.findById(placeId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 장소가 없음"));

        place.editDate(date);
    }

    @Transactional
    public void editOrder(Long placeId, Integer order) {
        Place place = placeRepository.findById(placeId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 장소가 없음"));

        place.editPlaceOrder(order);
    }

}
