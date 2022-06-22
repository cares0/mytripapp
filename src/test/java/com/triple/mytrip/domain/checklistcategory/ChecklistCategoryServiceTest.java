package com.triple.mytrip.domain.checklistcategory;

import com.triple.mytrip.domain.checklistcategory.checklist.Checklist;
import com.triple.mytrip.domain.checklistcategory.checklist.ChecklistService;
import com.triple.mytrip.domain.checklistcategory.ChecklistCategory;
import com.triple.mytrip.domain.checklistcategory.ChecklistCategoryService;
import com.triple.mytrip.domain.member.Member;
import com.triple.mytrip.domain.trip.Trip;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class ChecklistCategoryServiceTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private ChecklistCategoryService checklistCategoryService;

    @Test
    public void 체크리스트_저장() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        ChecklistCategory category = createCategory(trip, "카테고리1");
        Checklist checklist = Checklist.builder().name("체크리스트1").basicOfferStatus(false).checkStatus(false).build();

        // when
        Long savedId = checklistCategoryService.addChecklist(category.getId(), checklist);
        Checklist savedChecklist = em.find(Checklist.class, savedId);

        // then
        assertThat(savedChecklist).isEqualTo(checklist);
    }

    @Test
    public void 카테고리_수정() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        ChecklistCategory category = createCategory(trip, "카테고리1");

        em.flush();
        em.clear();

        // when
        ChecklistCategory modified = ChecklistCategory.builder().name("변경").build();
        checklistCategoryService.modify(category.getId(), modified);

        em.flush();
        em.clear();
        ChecklistCategory modifiedCategory = em.find(ChecklistCategory.class, category.getId());

        // then
        assertThat(modifiedCategory.getName()).isEqualTo("변경");
    }

    @Test
    public void 카테고리_삭제() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        ChecklistCategory category = createCategory(trip, "카테고리1");

        // when
        checklistCategoryService.remove(category.getId());

        // then
        assertThat(em.find(ChecklistCategory.class, category.getId())).isNull();
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

    private ChecklistCategory createCategory(Trip trip, String name) {
        ChecklistCategory category = ChecklistCategory.builder()
                .basicOfferStatus(false)
                .name(name)
                .build();
        category.addTrip(trip);
        em.persist(category);
        return category;
    }

}