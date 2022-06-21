package com.triple.mytrip.domain.schedule.flight;

import com.triple.mytrip.domain.exception.EntityNotFoundException;
import com.triple.mytrip.domain.flight.Flight;
import com.triple.mytrip.domain.flight.FlightService;
import com.triple.mytrip.domain.member.Member;
import com.triple.mytrip.domain.schedule.Schedule;
import com.triple.mytrip.domain.trip.Trip;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class FlightServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    FlightService flightService;

    @Test
    public void 항공_조회() throws Exception {
        // given
        Flight flight = createFlight("number1", "대한항공",
                LocalDate.of(2022, 06, 19), LocalTime.of(10, 10),
                LocalTime.of(12, 10), "인천공항", "제주공항");

        // when
        Flight findFlight = flightService.getOne(flight.getId());

        // then
        assertThat(findFlight).isEqualTo(flight);
    }

    @Test
    public void 항공_수정() throws Exception {
        // given
        Flight flight = createFlight("number1", "대한항공",
                LocalDate.of(2022, 06, 19), LocalTime.of(10, 10),
                LocalTime.of(12, 10), "인천공항", "제주공항");

        em.flush();
        em.clear();

        // when
        Flight modified = Flight.builder()
                .flightNumber("number2")
                .airline("수정항공")
                .departureDate(LocalDate.of(2022, 05, 19))
                .departureTime(LocalTime.of(10, 10))
                .arrivalTime(LocalTime.of(12, 10))
                .departureAirport("수정공항")
                .arrivalAirport("수정공항")
                .build();

        flightService.modify(flight.getId(), modified);

        em.flush();
        em.clear();

        modified = flightService.getOne(flight.getId());

        // then
        assertThat(modified.getFlightNumber()).isEqualTo("number2");
        assertThat(modified.getAirline()).isEqualTo("수정항공");
        assertThat(modified.getDepartureDate()).isEqualTo(LocalDate.of(2022, 05, 19));
        assertThat(modified.getDepartureTime()).isEqualTo(LocalTime.of(10, 10));
        assertThat(modified.getArrivalTime()).isEqualTo(LocalTime.of(12, 10));
        assertThat(modified.getDepartureAirport()).isEqualTo("수정공항");
        assertThat(modified.getArrivalAirport()).isEqualTo("수정공항");
    }

    @Test
    public void 항공_삭제() throws Exception {
        // given
        Flight flight = createFlight("number1", "대한항공",
                LocalDate.of(2022, 06, 19), LocalTime.of(10, 10),
                LocalTime.of(12, 10), "인천공항", "제주공항");

        // when
        flightService.remove(flight.getId());

        // then
        assertThatThrownBy(() -> flightService.getOne(flight.getId()))
                .isInstanceOf(EntityNotFoundException.class);
    }

    private Flight createFlight(String flightNumber, String airline, LocalDate departureDate, LocalTime departureTime, LocalTime arrivalTime, String departureAirport, String arrivalAirport) {
        Flight flight = Flight.builder()
                .flightNumber(flightNumber)
                .airline(airline)
                .departureDate(departureDate)
                .departureTime(departureTime)
                .arrivalTime(arrivalTime)
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .build();
        em.persist(flight);
        return flight;
    }

}