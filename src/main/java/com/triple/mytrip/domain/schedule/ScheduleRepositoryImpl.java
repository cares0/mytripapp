package com.triple.mytrip.domain.schedule;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.triple.mytrip.domain.flight.QFlight;
import com.triple.mytrip.domain.place.QPlace;


import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static com.triple.mytrip.domain.place.QPlace.*;
import static com.triple.mytrip.domain.schedule.QSchedule.*;


public class ScheduleRepositoryImpl implements ScheduleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ScheduleRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Schedule> findAllByTripIdWithAll(Long tripId) {
        return queryFactory
                .selectFrom(schedule)
                .leftJoin(schedule.flight, QFlight.flight).fetchJoin()
                .leftJoin(schedule.place, place).fetchJoin()
                .orderBy(schedule.date.asc(), schedule.arrangeOrder.asc())
                .fetch();
    }
}
