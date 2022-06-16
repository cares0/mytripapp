package com.triple.mytrip.domain.checklist.category;

import com.triple.mytrip.domain.member.Member;
import com.triple.mytrip.domain.trip.Trip;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ChecklistCategoryServiceTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private ChecklistCategoryService checklistCategoryService;

    @Test
    public void 카테고리_저장() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        ChecklistCategory category = createCategory();

        // when
        Long savedId = checklistCategoryService.save(trip.getId(), category);
        ChecklistCategory findCategory = em.find(category.getClass(), savedId);

        // then
        Assertions.assertThat(findCategory).isEqualTo(category);
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

    private ChecklistCategory createCategory() {
        return new ChecklistCategory("카테고리1");
    }

}