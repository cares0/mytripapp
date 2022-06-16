package com.triple.mytrip.domain.checklist.category;

import com.triple.mytrip.domain.member.Member;
import com.triple.mytrip.domain.trip.Trip;
import jdk.jfr.Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

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

        // when
        ChecklistCategory category = createCategory(trip.getId(), "카테고리1");
        ChecklistCategory findCategory = em.find(ChecklistCategory.class, category.getId());

        // then
        assertThat(findCategory).isEqualTo(category);
    }

    @Test
    public void 카테고리_이름수정() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        ChecklistCategory category = createCategory(trip.getId(), "카테고리1");

        em.flush();
        em.clear();

        // when
        checklistCategoryService.editName(category.getId(), "변경");

        // then
        ChecklistCategory modifiedCategory = em.find(ChecklistCategory.class, category.getId());
        assertThat(modifiedCategory.getName()).isEqualTo("변경");
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

    private ChecklistCategory createCategory(Long tripId, String name) {
        ChecklistCategory category = new ChecklistCategory(name);
        checklistCategoryService.save(tripId, category);
        return category;
    }

}