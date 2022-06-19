package com.triple.mytrip.domain.schedule.flight;

import com.triple.mytrip.domain.schedule.Schedule;
import com.triple.mytrip.domain.schedule.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final ScheduleRepository scheduleRepository;

    public Map<String, Long> save(Flight flight) {
        Flight savedFlight = flightRepository.save(flight);

        Schedule savedSchedule = saveSchedule(flight);

        Map<String, Long> idMap = makeIdMap(savedFlight, savedSchedule);
        return idMap;
    }

    private Map<String, Long> makeIdMap(Flight savedFlight, Schedule savedSchedule) {
        Map<String, Long> idMap = new HashMap<>();
        idMap.put("flightId", savedFlight.getId());
        idMap.put("scheduleId", savedSchedule.getId());
        return idMap;
    }

    private Schedule saveSchedule(Flight flight) {
        Schedule schedule = new Schedule(flight.getDepartureDate(), null, 0);
        schedule.addFlight(flight);
        schedule = scheduleRepository.save(schedule);
        return schedule;
    }
}
