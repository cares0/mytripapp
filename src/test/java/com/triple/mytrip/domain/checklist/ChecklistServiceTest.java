package com.triple.mytrip.domain.checklist;

import com.triple.mytrip.domain.checklist.category.ChecklistCategory;
import com.triple.mytrip.domain.exception.EntityNotFoundException;
import com.triple.mytrip.domain.member.Member;
import com.triple.mytrip.domain.trip.Trip;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ChecklistServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    ChecklistService checklistService;

    @Test
    public void 체크리스트_등록() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        ChecklistCategory category = createCategory(trip, "카테고리1");
        Checklist checklist = new Checklist("체크리스트1");

        // when
        Long savedId = checklistService.save(category.getId(), checklist);
        Checklist findChecklist = em.find(Checklist.class, savedId);

        // then
        assertThat(findChecklist.getId()).isEqualTo(savedId);
    }

    @Test
    public void 체크리스트_수정() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        ChecklistCategory category = createCategory(trip, "카테고리1");
        Checklist checklist = new Checklist("체크리스트1");
        Long savedId = checklistService.save(category.getId(), checklist);

        // when
        checklistService.editMemo(savedId, "메모수정");
        checklistService.editCheckStatus(savedId);
        checklistService.editName(savedId, "이름수정");
        em.flush();
        em.clear();

        Checklist modified = em.find(Checklist.class, checklist.getId());

        // then
        assertThat(modified.getCheckStatus()).isTrue();
        assertThat(modified.getMemo()).isEqualTo("메모수정");
        assertThat(modified.getName()).isEqualTo("이름수정");
    }

    @Test
    public void 체크리스트_삭제() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        ChecklistCategory category = createCategory(trip, "카테고리1");
        Checklist checklist = new Checklist("체크리스트1");
        Long savedId = checklistService.save(category.getId(), checklist);
        em.flush();
        em.clear();

        // when
        checklistService.delete(savedId);

        // then
        assertThatThrownBy(() -> checklistService.getOne(savedId))
                .isInstanceOf(EntityNotFoundException.class);
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

    private ChecklistCategory createCategory(Trip trip, String name) {
        ChecklistCategory category = new ChecklistCategory(name);
        category.addTrip(trip);
        em.persist(category);
        return category;
    }



}