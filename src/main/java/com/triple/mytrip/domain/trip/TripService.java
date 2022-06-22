package com.triple.mytrip.domain.trip;

import com.triple.mytrip.domain.budget.Budget;
import com.triple.mytrip.domain.budget.BudgetRepository;
import com.triple.mytrip.domain.checklistcategory.ChecklistCategory;
import com.triple.mytrip.domain.checklistcategory.ChecklistCategoryRepository;
import com.triple.mytrip.domain.common.Period;
import com.triple.mytrip.domain.exception.EntityNotFoundException;
import com.triple.mytrip.domain.exception.EntityNotWithinPeriodException;
import com.triple.mytrip.domain.flight.Flight;
import com.triple.mytrip.domain.flight.FlightRepository;
import com.triple.mytrip.domain.place.Place;
import com.triple.mytrip.domain.place.PlaceRepository;
import com.triple.mytrip.domain.schedule.Schedule;
import com.triple.mytrip.domain.schedule.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class TripService {

    private final TripRepository tripRepository;
    private final BudgetRepository budgetRepository;
    private final ScheduleRepository scheduleRepository;
    private final FlightRepository flightRepository;
    private final PlaceRepository placeRepository;
    private final ChecklistCategoryRepository checklistCategoryRepository;

    public Long addBudget(Long tripId, Budget budget) {
        Trip trip = findTrip(tripId);
        validateDate(trip, budget.getDate());

        budget.addTrip(trip);
        return budgetRepository.save(budget).getId();
    }

    public Long addChecklistCategory(Long tripId, ChecklistCategory checklistCategory) {
        Trip trip = findTrip(tripId);
        checklistCategory.addTrip(trip);
        return checklistCategoryRepository.save(checklistCategory).getId();
    }

    public Map<String, Long> addFlightSchedule(Long tripId, Flight flight) {
        Trip trip = findTrip(tripId);
        validateDate(trip, flight.getDepartureDate());

        Flight savedFlight = flightRepository.save(flight);
        Schedule savedSchedule = saveFilghtSchedule(trip, flight);
        return makeIdMap(savedFlight, savedSchedule);
    }

    public Long addPlaceSchedule(Long tripId, Long placeId, Schedule schedule) {
        Trip trip = findTrip(tripId);
        validateDate(trip, schedule.getDate());

        Place place = findPlace(placeId);
        schedule.addTrip(trip);
        schedule.addPlace(place);
        Schedule saved = scheduleRepository.save(schedule);
        return saved.getId();
    }

    @Transactional(readOnly = true)
    public Trip getOne(Long tripId) {
        return findTrip(tripId);
    }

    @Transactional(readOnly = true)
    public Trip getOneWithSchedule(Long tripId) {
        Trip trip = tripRepository.findAllByIdWithSchedule(tripId);
        initTripPeriodIfNull(trip);
        return trip;
    }

    @Transactional(readOnly = true)
    public Trip getOneWithChecklistCategory(Long tripId) {
        Trip trip = tripRepository.findAllByIdWithChecklistCategory(tripId);
        initChecklist(trip);
        return trip;
    }

    @Transactional(readOnly = true)
    public Trip getOneWithBudget(Long tripId) {
        return tripRepository.findAllByIdWithBudget(tripId);
    }

    public Trip modify(Long tripId, Trip modified) {
        Trip original = findTrip(tripId);

        update(original, modified);
        return original;
    }

    public void remove(Long tripId) {
        tripRepository.deleteById(tripId);
    }

    private void validateDate(Trip trip, LocalDate date) {
        initTripPeriodIfNull(trip);
        if (!trip.getPeriod().isWithinPeriod(date)) {
            String message = date + "는 "
                    + trip.getPeriod().getDepartureDate() + "와 "
                    + trip.getPeriod().getArrivalDate() + "사이에 속하지 않습니다.";
            throw new EntityNotWithinPeriodException(message);
        }
    }

    private void initTripPeriodIfNull(Trip trip) {
        if (Objects.isNull(trip.getPeriod())) {
            trip.addPeriod(Period.builder()
                    .departureDate(LocalDate.now())
                    .arrivalDate(LocalDate.now().plusDays(3))
                    .build());
        }
    }

    private Trip findTrip(Long tripId) {
        return tripRepository.findById(tripId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 여행을 찾을 수 없음"));
    }

    private Place findPlace(Long placeId) {
        return placeRepository.findById(placeId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 장소를 찾을 수 없음"));
    }

    private Schedule saveFilghtSchedule(Trip trip, Flight flight) {
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
        LocalDate arrivalDate = modified.getPeriod().getArrivalDate();
        if (Objects.nonNull(arrivalDate)) {
            original.editArrivalDate(arrivalDate);
        }
        LocalDate departureDate = modified.getPeriod().getDepartureDate();
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

    /**
     * 컬렉션 페치 조인 1번 이상 불가, 강제 지연로딩 초기화(batch-size로 N+1 해결)
     */
    private void initChecklist(Trip trip) {
        if (!CollectionUtils.isEmpty(trip.getChecklistCategories())) {
            trip.getChecklistCategories().get(0).getChecklists().size();
        }
    }

}
