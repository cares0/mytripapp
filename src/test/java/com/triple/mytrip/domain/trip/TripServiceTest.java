package com.triple.mytrip.domain.trip;

import com.triple.mytrip.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

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

    private Member createMember(String email, String password) {
        Member member = new Member(email, password);
        em.persist(member);
        return member;
    }

}