package com.triple.mytrip.domain.place.shop;

import com.triple.mytrip.domain.place.Place;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("SHOP")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shop extends Place {

    public Shop(String name, LocalDate date, String location, Integer placeOrder) {
        super(name, date, location, placeOrder);
    }

}
