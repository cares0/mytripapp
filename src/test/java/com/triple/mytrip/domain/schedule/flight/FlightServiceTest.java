package com.triple.mytrip.domain.schedule.flight;

import com.triple.mytrip.domain.member.Member;
import com.triple.mytrip.domain.place.Place;
import com.triple.mytrip.domain.schedule.Schedule;
import com.triple.mytrip.domain.trip.Trip;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

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
        Map<String, Long> idMap = flightService.save(flight);
        em.flush();
        em.clear();

        Schedule findSchedule = em.find(Schedule.class, idMap.get("scheduleId"));

        // then
        assertThat(findSchedule.getDate()).isEqualTo(LocalDate.of(2022, 06, 19));
        assertThat(findSchedule.getArrangeOrder()).isEqualTo(0);
        assertThat(findSchedule.getFlight().getAirline()).isEqualTo("대한항공");
        assertThat(findSchedule.getFlight().getFlightNumber()).isEqualTo("number1");
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