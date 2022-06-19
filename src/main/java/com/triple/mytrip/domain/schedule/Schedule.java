package com.triple.mytrip.domain.schedule;

import com.triple.mytrip.domain.place.Place;
import com.triple.mytrip.domain.schedule.flight.Flight;
import com.triple.mytrip.domain.trip.Trip;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @OneToOne(fetch = LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    private LocalDate date;
    private Integer visitOrder;
    private Integer arrangeOrder;

    private LocalTime visitTime;
    private String memo;

    /**
     * 저장 전용
     */
    public Schedule(LocalDate date, Integer visitOrder, Integer arrangeOrder) {
        this.date = date;
        this.visitOrder = visitOrder;
        this.arrangeOrder = arrangeOrder;
    }

    /**
     * 수정 전용
     */
    public Schedule(LocalDate date, Integer visitOrder, Integer arrangeOrder, LocalTime visitTime, String memo) {
        this.date = date;
        this.visitOrder = visitOrder;
        this.arrangeOrder = arrangeOrder;
        this.visitTime = visitTime;
        this.memo = memo;
    }

    public void addTrip(Trip trip) {
        this.trip = trip;
    }

    public void addPlace(Place place) {
        this.place = place;
    }

    public void addFlight(Flight flight) {
        this.flight = flight;
    }

    public void editVisitTime(LocalTime visitTime) {
        this.visitTime = visitTime;
    }

    public void editMemo(String memo) {
        this.memo = memo;
    }

    public void editDate(LocalDate date) {
        this.date = date;
    }

    public void editVisitOrder(Integer visitOrder) {
        this.visitOrder = visitOrder;
    }

    public void editArrangeOrder(Integer arrangeOrder) {
        this.arrangeOrder = arrangeOrder;
    }
}
