package com.triple.mytrip.domain.place;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Place {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "place_id")
    private Long id;

    private String name;

    private String location;

    private PlaceType placeType;

    private Double rating;

    public Place(String name, String location, PlaceType placeType) {
        this.name = name;
        this.location = location;
        this.placeType = placeType;
        this.rating = 0D;
    }
}
