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
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "place_type")
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
    private Integer placeOrder;
    private String location;

    private LocalTime visitTime;
    private String memo;

    public Place(String name, LocalDate date, String location, Integer placeOrder) {
        this.name = name;
        this.date = date;
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
