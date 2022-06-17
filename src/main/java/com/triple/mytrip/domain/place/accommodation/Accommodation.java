package com.triple.mytrip.domain.place.accommodation;

import com.triple.mytrip.domain.place.Place;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("ACCOMMODATION")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Accommodation extends Place {

    public Accommodation(String name, LocalDate date, String location, Integer placeOrder) {
        super(name, date, location, placeOrder);
    }
}
