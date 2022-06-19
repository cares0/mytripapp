package com.triple.mytrip.domain.schedule.flight;

import com.triple.mytrip.domain.exception.EntityNotFoundException;
import com.triple.mytrip.domain.schedule.Schedule;
import com.triple.mytrip.domain.schedule.ScheduleRepository;
import com.triple.mytrip.domain.trip.Trip;
import com.triple.mytrip.domain.trip.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FlightService {

    private final TripRepository tripRepository;
    private final FlightRepository flightRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public Map<String, Long> save(Long tripId, Flight flight) {
        Trip trip = findTrip(tripId);
        Flight savedFlight = flightRepository.save(flight);

        Schedule savedSchedule = saveSchedule(trip, flight);

        Map<String, Long> idMap = makeIdMap(savedFlight, savedSchedule);
        return idMap;
    }

    private Trip findTrip(Long tripId) {
        return tripRepository.findById(tripId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 여행을 찾을 수 없음"));
    }

    private Schedule saveSchedule(Trip trip, Flight flight) {
        Schedule schedule = new Schedule(flight.getDepartureDate(), null, 0);
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
}
