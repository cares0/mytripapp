package com.triple.mytrip.domain.checklistcategory;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.triple.mytrip.domain.checklistcategory.checklist.QChecklist;

import javax.persistence.EntityManager;
import java.util.List;

import static com.triple.mytrip.domain.checklistcategory.QChecklistCategory.*;
import static com.triple.mytrip.domain.checklistcategory.checklist.QChecklist.*;

public class ChecklistCategoryRepositoryImpl implements ChecklistCategoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ChecklistCategoryRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ChecklistCategory> findAllByTripIdWithChecklist(Long tripId) {
        return queryFactory
                .selectFrom(checklistCategory).distinct()
                .join(checklistCategory.checklists, checklist).fetchJoin()
                .where(checklistCategory.trip.id.eq(tripId))
                .fetch();
    }
}
