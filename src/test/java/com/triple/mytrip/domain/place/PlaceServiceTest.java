package com.triple.mytrip.domain.place;

import com.triple.mytrip.domain.common.TripCategory;
import com.triple.mytrip.domain.member.Member;
import com.triple.mytrip.domain.trip.Trip;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class PlaceServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    PlaceService placeService;

    @Test
    public void 장소_등록() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        Place place = new Place("장소1", LocalDate.now(),TripCategory.ETC, "위치1", 0);

        // when
        Long savedId = placeService.save(trip.getId(), place);
        Place findPlace = em.find(Place.class, savedId);

        // then
        assertThat(findPlace).isEqualTo(place);

    }

    @Test
    public void 장소_수정() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        Place place = createPlace(trip, "장소1", LocalDate.now(), TripCategory.ETC, "위치1", 0);

        // when
        placeService.editDate(place.getId(), LocalDate.of(2022, 5, 20));
        placeService.editMemo(place.getId(), "메모");
        placeService.editOrder(place.getId(), 1);
        placeService.editVisitTime(place.getId(), LocalTime.of(1, 10, 10));

        em.flush();
        em.clear();

        Place modified = em.find(Place.class, place.getId());
        // then
        assertThat(modified.getDate()).isEqualTo(LocalDate.of(2022, 5, 20));
        assertThat(modified.getMemo()).isEqualTo("메모");
        assertThat(modified.getPlaceOrder()).isEqualTo(1);
        assertThat(modified.getVisitTime()).isEqualTo(LocalTime.of(1, 10, 10));

    }

    private Place createPlace(Trip trip, String name, LocalDate date, TripCategory tripCategory, String position, int placeOrder) {
        Place place = new Place(name, date, tripCategory, position, placeOrder);
        place.addTrip(trip);
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