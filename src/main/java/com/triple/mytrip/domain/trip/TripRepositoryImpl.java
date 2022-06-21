package com.triple.mytrip.domain.trip;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.triple.mytrip.domain.budget.QBudget;
import com.triple.mytrip.domain.checklistcategory.QChecklistCategory;
import com.triple.mytrip.domain.checklistcategory.checklist.QChecklist;
import com.triple.mytrip.domain.flight.QFlight;

import javax.persistence.EntityManager;

import static com.triple.mytrip.domain.budget.QBudget.*;
import static com.triple.mytrip.domain.checklistcategory.QChecklistCategory.*;
import static com.triple.mytrip.domain.checklistcategory.checklist.QChecklist.*;
import static com.triple.mytrip.domain.flight.QFlight.*;
import static com.triple.mytrip.domain.place.QPlace.place;
import static com.triple.mytrip.domain.schedule.QSchedule.schedule;
import static com.triple.mytrip.domain.trip.QTrip.*;

public class TripRepositoryImpl implements TripRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public TripRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Trip findAllByIdWithSchedule(Long tripId) {
        return queryFactory
                .selectFrom(trip)
                .leftJoin(trip.schedules, schedule).fetchJoin()
                .leftJoin(schedule.flight, flight).fetchJoin()
                .leftJoin(schedule.place, place).fetchJoin()
                .where(trip.id.eq(tripId))
                .orderBy(schedule.date.asc(), schedule.arrangeOrder.asc())
                .fetchOne();
    }

    @Override
    public Trip findAllByIdWithChecklistCategory(Long tripId) {
        return queryFactory
                .select(trip).distinct()
                .from(trip)
                .leftJoin(trip.checklistCategories, checklistCategory).fetchJoin()
                .leftJoin(checklistCategory.checklists, checklist)
                .where(trip.id.eq(tripId))
                .fetchOne();
    }

    @Override
    public Trip findAllByIdWithBudget(Long tripId) {
        return queryFactory
                .selectFrom(trip)
                .leftJoin(trip.budgets, budget).fetchJoin()
                .where(trip.id.eq(tripId))
                .orderBy(budget.date.asc(), budget.order.asc())
                .fetchOne();
    }
}
