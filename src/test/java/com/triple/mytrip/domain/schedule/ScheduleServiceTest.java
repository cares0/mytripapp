package com.triple.mytrip.domain.schedule;

import com.triple.mytrip.domain.member.Member;
import com.triple.mytrip.domain.place.Place;
import com.triple.mytrip.domain.place.PlaceType;
import com.triple.mytrip.domain.flight.Flight;
import com.triple.mytrip.domain.trip.Trip;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ScheduleServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    ScheduleService scheduleService;

    @Test
    public void 일정_조회_연관포함() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        Place place = createPlace("장소1", "위치1", PlaceType.SHOP);
        Flight flight = createFlight("number1", "대한항공",
                LocalDate.of(2022, 06, 19), LocalTime.of(10, 10),
                LocalTime.of(12, 10), "인천공항", "제주공항");
        Schedule schedule1 = createSchedule(trip, place, null, LocalDate.of(2022, 06, 19), 0, 0);
        Schedule schedule2 = createSchedule(trip, null, flight, LocalDate.of(2022, 06, 19), 0, 0);
        // when
        Schedule findSchedule1 = scheduleService.getOneWithAll(schedule1.getId());
        Schedule findSchedule2 = scheduleService.getOneWithAll(schedule2.getId());

        // then
        assertThat(findSchedule1).isEqualTo(schedule1);
        assertThat(findSchedule2).isEqualTo(schedule2);
        assertThat(findSchedule1.getPlace()).isEqualTo(place);
        assertThat(findSchedule2.getFlight()).isEqualTo(flight);
    }

    @Test
    public void 일정_수정() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        Place place = createPlace("장소1", "위치1", PlaceType.SHOP);
        Schedule schedule = createSchedule(trip, place, null, LocalDate.of(2022, 06, 19), 0, 0);

        em.flush();
        em.clear();

        Schedule modified = Schedule.builder()
                .date(LocalDate.of(2022, 07, 19))
                .visitOrder(2)
                .arrangeOrder(0)
                .visitTime(LocalTime.of(10, 10))
                .memo("메모변경")
                .build();

        // when
        scheduleService.modify(schedule.getId(), modified);

        em.flush();
        em.clear();

        modified = em.find(Schedule.class, schedule.getId());

        // then
        assertThat(modified.getDate()).isEqualTo(LocalDate.of(2022, 07, 19));
        assertThat(modified.getVisitOrder()).isEqualTo(2);
        assertThat(modified.getArrangeOrder()).isEqualTo(0); // 변경 X
        assertThat(modified.getVisitTime()).isEqualTo(LocalTime.of(10, 10));
        assertThat(modified.getMemo()).isEqualTo("메모변경");
    }

    @Test
    public void 일정_삭제() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        Place place = createPlace("장소1", "위치1", PlaceType.SHOP);
        Schedule schedule = createSchedule(trip, place, null, LocalDate.of(2022, 06, 19), 0, 0);

        // when
        scheduleService.remove(schedule.getId());

        // then
        assertThat(em.find(Schedule.class, schedule.getId())).isNull();
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

    private Schedule createSchedule(Trip trip, Place place, Flight flight, LocalDate date, Integer visitOrder, Integer arrangeOrder) {
        Schedule schedule = Schedule.builder()
                .date(date)
                .visitOrder(visitOrder)
                .arrangeOrder(arrangeOrder)
                .build();

        schedule.addPlace(place);
        schedule.addTrip(trip);
        schedule.addFlight(flight);
        em.persist(schedule);
        return schedule;
    }

    private Place createPlace(String name, String location, PlaceType placeType) {
        Place place = Place.builder()
                .name(name)
                .location(location)
                .placeType(placeType)
                .build();
        em.persist(place);
        return place;
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