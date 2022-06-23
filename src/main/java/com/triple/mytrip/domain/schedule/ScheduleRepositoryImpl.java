package com.triple.mytrip.domain.schedule;

import com.querydsl.jpa.impl.JPAQueryFactory;


import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.triple.mytrip.domain.flight.QFlight.*;
import static com.triple.mytrip.domain.place.QPlace.*;
import static com.triple.mytrip.domain.schedule.QSchedule.*;


public class ScheduleRepositoryImpl implements ScheduleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ScheduleRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Schedule> findByIdWithAll(Long scheduleId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(QSchedule.schedule)
                .leftJoin(QSchedule.schedule.place, place).fetchJoin()
                .leftJoin(QSchedule.schedule.flight, flight).fetchJoin()
                .where(QSchedule.schedule.id.eq(scheduleId))
                .fetchOne());
    }

}
