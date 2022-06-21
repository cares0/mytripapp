package com.triple.mytrip.domain.checklist.category;

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

    @Autowired
    private ChecklistService checklistService;

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
        ChecklistCategory modified = ChecklistCategory.builder().name("변경").build();
        checklistCategoryService.modify(category.getId(), modified);

        // then
        ChecklistCategory modifiedCategory = em.find(ChecklistCategory.class, category.getId());
        assertThat(modifiedCategory.getName()).isEqualTo("변경");
    }

    @Test
    public void 전체조회_체크리스트포함() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        ChecklistCategory category1 = createCategory(trip.getId(), "카테고리1");
        ChecklistCategory category2 = createCategory(trip.getId(), "카테고리2");
        Checklist checklist1 = createChecklist(category1.getId(), "체크리스트1");
        Checklist checklist2 = createChecklist(category1.getId(), "체크리스트2");
        Checklist checklist3 = createChecklist(category2.getId(), "체크리스트3");
        Checklist checklist4 = createChecklist(category2.getId(), "체크리스트4");

        em.flush();
        em.clear();
        // when
        List<ChecklistCategory> results = checklistCategoryService.getListWithChecklist(trip.getId());

        // then
        assertThat(results).extracting("name").containsExactly("카테고리1", "카테고리2");
        assertThat(results.get(0).getChecklists()).extracting("name")
                .containsExactly("체크리스트1", "체크리스트2");
        assertThat(results.get(1).getChecklists()).extracting("name")
                .containsExactly("체크리스트3", "체크리스트4");
    }

    private Checklist createChecklist(Long categoryId, String name) {
        Checklist checklist = Checklist.builder().name(name).build();
        checklistService.add(categoryId, checklist);
        return checklist;
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

    private ChecklistCategory createCategory(Long tripId, String name) {
        ChecklistCategory category = ChecklistCategory.builder().basicOfferStatus(false).name(name).build();
        checklistCategoryService.add(tripId, category);
        return category;
    }

}