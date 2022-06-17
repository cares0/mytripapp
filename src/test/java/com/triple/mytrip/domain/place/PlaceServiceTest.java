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
        Assertions.assertThat(findPlace).isEqualTo(place);


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