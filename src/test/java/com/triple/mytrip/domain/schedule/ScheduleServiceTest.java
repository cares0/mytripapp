package com.triple.mytrip.domain.schedule;

import com.triple.mytrip.domain.member.Member;
import com.triple.mytrip.domain.place.Place;
import com.triple.mytrip.domain.place.PlaceType;
import com.triple.mytrip.domain.schedule.flight.Flight;
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
    public void 일정_저장() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        Place place = createPlace("장소1", "위치1", PlaceType.SHOP);

        Schedule schedule = new Schedule(LocalDate.of(2022, 06, 19), 0, 0);
        // when
        Long savedId = scheduleService.save(trip.getId(), place.getId(), schedule);

        em.flush();
        em.clear();

        Schedule findSchedule = em.find(Schedule.class, savedId);
        // then
        assertThat(findSchedule.getArrangeOrder()).isEqualTo(0);
        assertThat(findSchedule.getPlace().getLocation()).isEqualTo("위치1");
        assertThat(findSchedule.getTrip().getCity()).isEqualTo("제주");
    }
    
    @Test
    public void 일정_전체조회() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        
        Place place1 = createPlace("장소1", "위치1", PlaceType.SHOP);
        Schedule schedule1 = createSchedule(trip, place1, LocalDate.of(2022, 06, 20), 1, 1);
        Schedule schedule2 = createSchedule(trip, place1, LocalDate.of(2022, 06, 20), 2, 2);

        Place place2 = createPlace("장소2", "위치2", PlaceType.RESTAURANT);
        Schedule schedule3 = createSchedule(trip, place2, LocalDate.of(2022, 06, 19), 2, 2);
        Schedule schedule4 = createSchedule(trip, place1, LocalDate.of(2022, 06, 19), 1, 1);
        Schedule schedule5 = createSchedule(trip, place1, LocalDate.of(2022, 06, 19), 3, 3);

        Flight flight = createFlight(trip, "number1", "대한항공",
                LocalDate.of(2022, 06, 20), LocalTime.of(10, 10),
                LocalTime.of(12, 10), "인천공항", "제주공항");

        // when
        List<Schedule> result = scheduleService.getList(trip.getId());

        // then
        assertThat(result.get(0)).isEqualTo(schedule4);
        assertThat(result.get(1)).isEqualTo(schedule3);
        assertThat(result.get(2)).isEqualTo(schedule5);
        assertThat(result.get(3).getFlight()).isEqualTo(flight);
        assertThat(result.get(4)).isEqualTo(schedule1);
        assertThat(result.get(5)).isEqualTo(schedule2);
    }

    @Test
    public void 일정_수정() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        Place place = createPlace("장소1", "위치1", PlaceType.SHOP);
        Schedule schedule = createSchedule(trip, place, LocalDate.of(2022, 06, 19), 0, 0);

        em.flush();
        em.clear();

        Schedule modified = new Schedule(LocalDate.of(2022, 07, 19), 2, 0,
                LocalTime.of(10, 10), "메모변경");

        // when
        scheduleService.edit(schedule.getId(), modified);

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

    private Flight createFlight(Trip trip, String flightNumber, String airline, LocalDate departureDate, LocalTime departureTime, LocalTime arrivalTime, String departureAirport, String arrivalAirport) {
        Flight flight = new Flight(flightNumber, airline, departureDate, departureTime, arrivalTime, departureAirport, arrivalAirport);
        Schedule schedule = new Schedule(flight.getDepartureDate(), null, 0);
        em.persist(flight);
        schedule.addTrip(trip);
        schedule.addFlight(flight);
        em.persist(schedule);
        return flight;
    }
    
    private Schedule createSchedule(Trip trip, Place place, LocalDate date, Integer visitOrder, Integer arrangeOrder) {
        Schedule schedule = new Schedule(date, visitOrder, arrangeOrder);
        Long savedId = scheduleService.save(trip.getId(), place.getId(), schedule);
        return schedule;
    }

    private Place createPlace(String name, String location, PlaceType placeType) {
        Place place = new Place(name, location, placeType);
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