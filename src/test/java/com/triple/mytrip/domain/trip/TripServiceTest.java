package com.triple.mytrip.domain.trip;

import com.triple.mytrip.domain.member.Member;
import com.triple.mytrip.domain.place.Place;
import com.triple.mytrip.domain.place.PlaceType;
import com.triple.mytrip.domain.schedule.Schedule;
import com.triple.mytrip.domain.schedule.flight.Flight;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TripServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    TripService tripService;

    @Test
    public void 여행_저장() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = new Trip("제주");

        // when
        Long savedId = tripService.save(member.getId(), trip);

        // then
        assertThat(trip).isEqualTo(em.find(Trip.class, savedId));
        assertThat(trip.getTitle()).isEqualTo("제주 여행");
    }

    @Test
    @Rollback(value = false)
    public void 여행_조회() throws Exception {
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
        em.flush();
        em.clear();

        Trip find = tripService.getTripWithSchedule(trip.getId());

        // then
    }

    @Test
    public void 여행_수정() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");

        em.flush();
        em.clear();

        // when
        Trip modified = new Trip("제주", "제목수정", LocalDate.of(2022, 01, 02),
                LocalDate.of(2022, 02, 01), Partner.CHILD, null);

        tripService.edit(trip.getId(), modified);
        em.flush();
        em.clear();

        modified = em.find(Trip.class, trip.getId());
        // then
        assertThat(modified.getCity()).isEqualTo("제주");
        assertThat(modified.getTitle()).isEqualTo("제목수정");
        assertThat(modified.getArrivalDate()).isEqualTo(LocalDate.of(2022, 01, 02));
        assertThat(modified.getDepartureDate()).isEqualTo(LocalDate.of(2022, 02, 01));
        assertThat(modified.getPartner()).isEqualTo(Partner.CHILD);
        assertThat(modified.getTripStyle()).isEqualTo(null);
    }

    @Test
    public void 여행_삭제() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");

        em.flush();
        em.clear();
        // when
        tripService.delete(trip.getId());
        em.flush();
        em.clear();
        Trip deletedTrip = em.find(Trip.class, trip.getId());

        // then
        assertThat(deletedTrip).isNull();
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
        schedule.addTrip(trip);
        schedule.addPlace(place);
        em.persist(schedule);
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