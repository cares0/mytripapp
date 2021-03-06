package com.triple.mytrip.domain.schedule;

import com.triple.mytrip.domain.common.BaseEntity;
import com.triple.mytrip.domain.exception.EntityNotWithinPeriodException;
import com.triple.mytrip.domain.flight.Flight;
import com.triple.mytrip.domain.place.Place;
import com.triple.mytrip.domain.trip.Trip;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Schedule extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @OneToOne(fetch = LAZY, cascade = REMOVE, orphanRemoval = true)
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @Column(nullable = false)
    private LocalDate date;

    private Integer visitOrder;

    @Column(nullable = false)
    private Integer arrangeOrder;

    private LocalTime visitTime;
    private String memo;

    @Builder
    private Schedule(LocalDate date, Integer visitOrder, Integer arrangeOrder, LocalTime visitTime, String memo) {
        this.date = date;
        this.visitOrder = visitOrder;
        this.arrangeOrder = arrangeOrder;
        this.visitTime = visitTime;
        this.memo = memo;
    }

    public void addTrip(Trip trip) {
        validateDate(trip, date);
        this.trip = trip;
        trip.getSchedules().add(this);
    }

    public void addPlace(Place place) {
        this.place = place;
    }

    public void addFlight(Flight flight) {
        validateDate(trip, flight.getDepartureDate());
        this.flight = flight;
    }

    public void editVisitTime(LocalTime visitTime) {
        this.visitTime = visitTime;
    }

    public void editMemo(String memo) {
        this.memo = memo;
    }

    public void editDate(LocalDate date) {
        validateDate(trip, date);
        this.date = date;
    }

    public void editVisitOrder(Integer visitOrder) {
        this.visitOrder = visitOrder;
    }

    public void editArrangeOrder(Integer arrangeOrder) {
        this.arrangeOrder = arrangeOrder;
    }

    private void validateDate(Trip trip, LocalDate date) {
        if (!trip.getPeriod().isWithinPeriod(date)) {
            throw new EntityNotWithinPeriodException(date,
                    trip.getPeriod().getDepartureDate(),
                    trip.getPeriod().getArrivalDate());
        }
    }
}
