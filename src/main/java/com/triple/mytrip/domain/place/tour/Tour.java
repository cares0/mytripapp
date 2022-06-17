package com.triple.mytrip.domain.place.tour;

import com.triple.mytrip.domain.place.Place;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;


@Entity
@DiscriminatorValue("TOUR")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tour extends Place {

    public Tour(String name, LocalDate date, String location, Integer placeOrder) {
        super(name, date, location, placeOrder);
    }

}
