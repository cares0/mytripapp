package com.triple.mytrip.domain.trip;

import com.triple.mytrip.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDate;

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