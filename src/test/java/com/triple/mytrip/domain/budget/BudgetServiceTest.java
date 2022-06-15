package com.triple.mytrip.domain.budget;

import com.triple.mytrip.domain.common.TripCategory;
import com.triple.mytrip.domain.member.Member;
import com.triple.mytrip.domain.trip.Trip;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class BudgetServiceTest {

    @Autowired
    BudgetService budgetService;

    @Autowired
    EntityManager em;

    @Test
    public void 단일조회() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        Budget budget = createBudget(trip, 10000, "숙소");

        // when
        Budget findBudget = budgetService.getOne(budget.getId());

        // then
        assertThat(findBudget).isEqualTo(budget);
    }

    @Test
    public void 전체조회() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip1 = createTrip(member, "제주");
        Trip trip2 = createTrip(member, "경기");
        Budget budget1 = createBudget(trip1, 10000, "숙소");
        Budget budget2 = createBudget(trip2, 20000, "숙소");
        Budget budget3 = createBudget(trip1, 30000, "음식점");
        Budget budget4 = createBudget(trip2, 40000, "음식점");

        // when
        List<Budget> result = budgetService.getList(trip1);

        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(budget1, budget3);
        assertThat(result).extracting("price").containsExactly(10000, 30000);
    }

    private Budget createBudget(Trip trip, int price, String place) {
        Budget budget = new Budget(trip, TripCategory.ACCOMMODATIONS, price, LocalDate.now(), PaymentPlan.CARD, 0, place);
        em.persist(budget);
        return budget;
    }

    private Trip createTrip(Member member, String city) {
        Trip trip = new Trip(member, city);
        em.persist(trip);
        return trip;
    }

    private Member createMember(String email, String password) {
        Member member = new Member(email, password);
        em.persist(member);
        return member;
    }

}