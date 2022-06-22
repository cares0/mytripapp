package com.triple.mytrip.domain.budget;

import com.triple.mytrip.domain.budget.budgetfile.BudgetFile;
import com.triple.mytrip.domain.exception.EntityNotFoundException;
import com.triple.mytrip.domain.member.Member;
import com.triple.mytrip.domain.trip.Trip;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class BudgetServiceTest {

    @Autowired
    BudgetService budgetService;

    @Autowired
    EntityManager em;

    @Test
    public void 가계부_단일조회() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        Budget budget = createBudget(trip, 10000, "숙소");
        BudgetFile budgetFile1 = createBudgetFile(budget, "fileName1", "oriName1");
        BudgetFile budgetFile2 = createBudgetFile(budget, "fileName2", "oriName2");

        em.flush();
        em.clear();
        // when
        Budget findBudget = budgetService.getOneWithBudgetFile(budget.getId());
        System.out.println("findBudget.getBudgetFiles().size() = " + findBudget.getBudgetFiles().size());
        // then
        assertThat(findBudget.getId()).isEqualTo(budget.getId());
        assertThat(findBudget.getBudgetFiles()).extracting("oriName").containsExactly("oriName1", "oriName2");
        assertThat(findBudget.getBudgetFiles()).extracting("fileName").containsExactly("fileName1", "fileName2");

    }

    private BudgetFile createBudgetFile(Budget budget, String fileName, String oriName) {
        BudgetFile budgetFile = BudgetFile.builder().fileName(fileName).oriName(oriName).build();
        budgetFile.addBudget(budget);
        em.persist(budgetFile);
        return budgetFile;
    }

    @Test
    public void 가계부_수정() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        Budget budget = createBudget(trip, 10000, "숙소");

        em.flush();
        em.clear();
        // 기존 ID로 조회 후 변경 내용 확인

        Budget modified = Budget.builder()
                .budgetCategory(BudgetCategory.ETC)
                .price(10000)
                .date(LocalDate.of(2020, 12, 22))
                .paymentPlan(PaymentPlan.CASH)
                .order(3)
                .place("수정장소")
                .content("수정컨텐츠")
                .build();
        modified = budgetService.modify(budget.getId(), modified);

        // then
        assertThat(modified.getPrice()).isEqualTo(10000); // 이것만 동일
        assertThat(modified.getPlace()).isEqualTo("수정장소");
        assertThat(modified.getContent()).isEqualTo("수정컨텐츠");
        assertThat(modified.getBudgetCategory()).isEqualTo(BudgetCategory.ETC);
        assertThat(modified.getPaymentPlan()).isEqualTo(PaymentPlan.CASH);
    }

    @Test
    public void 가계부_삭제() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        Budget budget = createBudget(trip, 10000, "숙소");
        em.flush();
        em.clear();

        // when
        budgetService.remove(budget.getId());


        // then
        assertThatThrownBy(() -> budgetService.getOneWithBudgetFile(budget.getId()))
                .isInstanceOf(EntityNotFoundException.class);
    }

    private Budget createBudget(Trip trip, int price, String place) {
        Budget budget = Budget.builder()
                .budgetCategory(BudgetCategory.ACCOMMODATIONS)
                .price(price)
                .date(LocalDate.now())
                .paymentPlan(PaymentPlan.CARD)
                .order(0)
                .place(place)
                .content("content1")
                .build();
        budget.addTrip(trip);
        em.persist(budget);
        return budget;
    }

    private Trip createTrip(Member member, String city) {
        Trip trip = Trip.builder().city(city).title(city + " 여행").build();
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