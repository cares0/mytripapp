package com.triple.mytrip.domain.place.restaurant;

import com.triple.mytrip.domain.place.Place;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("RESTAURANT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant extends Place {

    public Restaurant(String name, LocalDate date, String location, Integer placeOrder) {
        super(name, date, location, placeOrder);
    }

}
