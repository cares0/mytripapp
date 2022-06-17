package com.triple.mytrip.domain.place;

import com.triple.mytrip.domain.common.TripCategory;
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
public class Place {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip;
    private String name;
    private LocalDate date;

    @Enumerated(value = EnumType.STRING)
    private TripCategory tripCategory;
    private LocalTime visitTime;
    private String location;
    private Integer placeOrder;
    private String memo;

    public Place(String name, LocalDate date, TripCategory tripCategory,
                 String location, Integer placeOrder) {
        this.name = name;
        this.date = date;
        this.tripCategory = tripCategory;
        this.location = location;
        this.placeOrder = placeOrder;
    }

    public void addTrip(Trip trip) {
        this.trip = trip;
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

    public void editPlaceOrder(Integer placeOrder) {
        this.placeOrder = placeOrder;
    }

}
