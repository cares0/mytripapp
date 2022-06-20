package com.triple.mytrip.domain.schedule.flight;

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

@SpringBootTest
@Transactional
class FlightServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    FlightService flightService;

    @Test
    public void 항공_저장() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        Flight flight = new Flight("number1", "대한항공", LocalDate.of(2022, 06, 19),
                LocalTime.of(10, 10), LocalTime.of(12, 10), "인천공항", "제주공항");

        // when
        Map<String, Long> idMap = flightService.save(trip.getId(), flight);
        em.flush();
        em.clear();

        Schedule findSchedule = em.find(Schedule.class, idMap.get("scheduleId"));

        // then
        assertThat(findSchedule.getDate()).isEqualTo(LocalDate.of(2022, 06, 19));
        assertThat(findSchedule.getArrangeOrder()).isEqualTo(0);
        assertThat(findSchedule.getFlight().getAirline()).isEqualTo("대한항공");
        assertThat(findSchedule.getFlight().getFlightNumber()).isEqualTo("number1");
    }

    @Test
    public void 항공_수정() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        Flight flight = createFlight(trip, "number1", "대한항공",
                LocalDate.of(2022, 06, 19), LocalTime.of(10, 10),
                LocalTime.of(12, 10), "인천공항", "제주공항");

        em.flush();
        em.clear();

        // when
        Flight modified = new Flight("number2", "수정항공",
                LocalDate.of(2022, 05, 19), LocalTime.of(10, 10),
                LocalTime.of(12, 10), "수정공항", "수정공항");

        flightService.edit(flight.getId(), modified);

        em.flush();
        em.clear();

        modified = em.find(Flight.class, flight.getId());

        // then
        assertThat(modified.getFlightNumber()).isEqualTo("number2");
        assertThat(modified.getAirline()).isEqualTo("수정항공");
        assertThat(modified.getDepartureDate()).isEqualTo(LocalDate.of(2022, 05, 19));
        assertThat(modified.getDepartureTime()).isEqualTo(LocalTime.of(10, 10));
        assertThat(modified.getArrivalTime()).isEqualTo(LocalTime.of(12, 10));
        assertThat(modified.getDepartureAirport()).isEqualTo("수정공항");
        assertThat(modified.getArrivalAirport()).isEqualTo("수정공항");
    }

    private Flight createFlight(Trip trip, String flightNumber, String airline, LocalDate departureDate, LocalTime departureTime, LocalTime arrivalTime, String departureAirport, String arrivalAirport) {
        Flight flight = new Flight(flightNumber, airline, departureDate, departureTime, arrivalTime, departureAirport, arrivalAirport);
        flightService.save(trip.getId(), flight);
        return flight;
    }

    private Trip createTrip(Member member, String city) {
        Trip trip = new Trip(city);
        trip.addMember(member);
        em.persist(trip);
        return trip;
    }

    private Member createMember(String email, String password) {
        Member member = new Member(email, password);
        em.persist(member);
        return member;
    }

}