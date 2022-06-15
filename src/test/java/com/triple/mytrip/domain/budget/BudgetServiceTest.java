package com.triple.mytrip.domain.budget;

import com.triple.mytrip.domain.common.TripCategory;
import com.triple.mytrip.domain.exception.EntityNotFoundException;
import com.triple.mytrip.domain.member.Member;
import com.triple.mytrip.domain.trip.Trip;
import org.assertj.core.api.Assertions;
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
        List<Budget> result = budgetService.getList(trip1.getId());

        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(budget1, budget3);
        assertThat(result).extracting("price").containsExactly(10000, 30000);
    }

    @Test
    public void 전체필드수정() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        Budget budget = createBudget(trip, 10000, "숙소");

        em.flush();
        em.clear();

        // when
        // 영속성 컨텍스트 초기화 후 기존 ID를 가지고 있는 Budget 객체가 클라이언트에서 넘어왔다고 가정
        // 그리고 해당 Budget 객체를 엔티티 로직으로 일단 수정 (변경감지 안됨)
        budget.editAll(TripCategory.FLIGHT, 20000,
                LocalDate.now(), PaymentPlan.CARD,"숙소", "컨텐츠1");

        // 수정된 budget 객체를 넘김 -> 영속성 컨텍스트에 기존 데이터가 조회 -> 변경된 데이터 변경감지 시작
        budgetService.editAll(budget);

        em.flush();
        em.clear();
        // 기존 ID로 조회 후 변경 내용 확인
        Budget modifiedBudget = budgetService.getOne(budget.getId());

        // then
        assertThat(modifiedBudget.getPrice()).isEqualTo(20000);
        assertThat(modifiedBudget.getPlace()).isEqualTo("숙소"); // 이것만 변경 X
        assertThat(modifiedBudget.getContent()).isEqualTo("컨텐츠1");
        assertThat(modifiedBudget.getTripCategory()).isEqualTo(TripCategory.FLIGHT);
        assertThat(modifiedBudget.getPaymentPlan()).isEqualTo(PaymentPlan.CARD);
    }

    @Test
    public void 삭제() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        Budget budget = createBudget(trip, 10000, "숙소");
        em.flush();
        em.clear();

        // when
        budgetService.delete(budget.getId());

        // then
        assertThatThrownBy(() -> budgetService.getOne(budget.getId()))
                .isInstanceOf(EntityNotFoundException.class);
    }

    private Budget createBudget(Trip trip, int price, String place) {
        Budget budget = new Budget(TripCategory.ACCOMMODATIONS, price, LocalDate.now(), PaymentPlan.CARD, 0, place, "content1");
        budgetService.save(budget, trip.getId());
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