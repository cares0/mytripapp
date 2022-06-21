package com.triple.mytrip.domain.trip;

import com.triple.mytrip.domain.checklistcategory.ChecklistCategory;
import com.triple.mytrip.domain.checklistcategory.ChecklistCategoryRepository;
import com.triple.mytrip.domain.exception.EntityNotFoundException;
import com.triple.mytrip.domain.flight.Flight;
import com.triple.mytrip.domain.flight.FlightRepository;
import com.triple.mytrip.domain.member.Member;
import com.triple.mytrip.domain.member.MemberRepository;
import com.triple.mytrip.domain.place.Place;
import com.triple.mytrip.domain.place.PlaceRepository;
import com.triple.mytrip.domain.schedule.Schedule;
import com.triple.mytrip.domain.schedule.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class TripService {

    private final MemberRepository memberRepository;
    private final TripRepository tripRepository;
    private final ScheduleRepository scheduleRepository;
    private final FlightRepository flightRepository;
    private final PlaceRepository placeRepository;
    private final ChecklistCategoryRepository checklistCategoryRepository;

    public Long save(Long memberId, Trip trip) {
        Member member = memberRepository.findById(memberId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 회원을 찾을 수 없음"));

        trip.addMember(member);
        Trip saved = tripRepository.save(trip);
        return saved.getId();
    }

    public Long addChecklistCategory(Long tripId, ChecklistCategory checklistCategory) {
        Trip trip = findTrip(tripId);
        checklistCategory.addTrip(trip);
        ChecklistCategory saved = checklistCategoryRepository.save(checklistCategory);
        return saved.getId();
    }

    public Map<String, Long> addFlightSchedule(Long tripId, Flight flight) {
        Trip trip = findTrip(tripId);
        Flight savedFlight = flightRepository.save(flight);

        Schedule savedSchedule = saveSchedule(trip, flight);
        return makeIdMap(savedFlight, savedSchedule);
    }

    public Long addPlaceSchedule(Long tripId, Long placeId, Schedule schedule) {
        Trip trip = findTrip(tripId);
        Place place = findPlace(placeId);

        schedule.addTrip(trip);
        schedule.addPlace(place);
        Schedule saved = scheduleRepository.save(schedule);
        return saved.getId();
    }

    @Transactional(readOnly = true)
    public Trip getTripWithSchedule(Long tripId) {
        Trip trip = tripRepository.findAllByIdWithSchedule(tripId);
        return trip;
    }

    public Trip edit(Long tripId, Trip modified) {
        Trip original = tripRepository.findById(tripId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 여행을 찾을 수 없음"));

        update(original, modified);
        return original;
    }

    public void delete(Long tripId) {
        tripRepository.deleteById(tripId);
    }

    private Trip findTrip(Long tripId) {
        return tripRepository.findById(tripId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 여행을 찾을 수 없음"));
    }

    private Place findPlace(Long placeId) {
        return placeRepository.findById(placeId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 장소를 찾을 수 없음"));
    }

    private Schedule saveSchedule(Trip trip, Flight flight) {
        Schedule schedule = Schedule.builder()
                .date(flight.getDepartureDate())
                .visitOrder(null)
                .arrangeOrder(0)
                .build();
        schedule.addTrip(trip);
        schedule.addFlight(flight);
        schedule = scheduleRepository.save(schedule);
        return schedule;
    }

    private Map<String, Long> makeIdMap(Flight savedFlight, Schedule savedSchedule) {
        Map<String, Long> idMap = new HashMap<>();
        idMap.put("flightId", savedFlight.getId());
        idMap.put("scheduleId", savedSchedule.getId());
        return idMap;
    }

    private void update(Trip original, Trip modified) {
        String city = modified.getCity();
        if (Objects.nonNull(city)) {
            original.editCity(city);
        }
        String title = modified.getTitle();
        if (Objects.nonNull(title)) {
            original.editTitle(title);
        }
        LocalDate arrivalDate = modified.getArrivalDate();
        if (Objects.nonNull(arrivalDate)) {
            original.editArrivalDate(arrivalDate);
        }
        LocalDate departureDate = modified.getDepartureDate();
        if (Objects.nonNull(departureDate)) {
            original.editDepartureDate(departureDate);
        }
        Partner partner = modified.getPartner();
        if (Objects.nonNull(partner)) {
            original.editPartner(partner);
        }
        TripStyle tripStyle = modified.getTripStyle();
        if (Objects.nonNull(tripStyle)) {
            original.editTripStyle(tripStyle);
        }
    }


}
