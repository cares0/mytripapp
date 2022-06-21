package com.triple.mytrip.domain.flight;

import com.triple.mytrip.domain.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    @Transactional(readOnly = true)
    public Flight getOne(Long flightId) {
        return findFlight(flightId);
    }

    public Flight modify(Long flightId, Flight modified) {
        Flight original = findFlight(flightId);
        update(original, modified);
        return original;
    }

    public void remove(Long flightId) {
        flightRepository.deleteById(flightId);
    }

    private Flight findFlight(Long flightId) {
        return flightRepository.findById(flightId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 항공을 찾을 수 없습니다."));
    }

    private void update(Flight original, Flight modified) {
        String flightNumber = modified.getFlightNumber();
        if (Objects.nonNull(flightNumber)) {
            original.editFlightNumber(flightNumber);
        }
        String airline = modified.getAirline();
        if (Objects.nonNull(airline)) {
            original.editAirline(airline);
        }
        LocalDate departureDate = modified.getDepartureDate();
        if (Objects.nonNull(departureDate)) {
            original.editDepartureDate(departureDate);
        }
        LocalTime departureTime = modified.getDepartureTime();
        if (Objects.nonNull(departureTime)) {
            original.editDepartureTime(departureTime);
        }
        LocalTime arrivalTime = modified.getArrivalTime();
        if (Objects.nonNull(arrivalTime)) {
            original.editArrivalTime(arrivalTime);
        }
        String departureAirport = modified.getDepartureAirport();
        if (Objects.nonNull(departureAirport)) {
            original.editDepartureAirport(departureAirport);
        }
        String arrivalAirport = modified.getArrivalAirport();
        if (Objects.nonNull(arrivalAirport)) {
            original.editArrivalAirport(arrivalAirport);
        }
    }
}
