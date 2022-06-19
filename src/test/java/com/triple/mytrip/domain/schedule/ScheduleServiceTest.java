package com.triple.mytrip.domain.schedule;

import com.triple.mytrip.domain.member.Member;
import com.triple.mytrip.domain.place.Place;
import com.triple.mytrip.domain.place.PlaceType;
import com.triple.mytrip.domain.trip.Trip;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;

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