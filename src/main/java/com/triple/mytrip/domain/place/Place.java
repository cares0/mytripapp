package com.triple.mytrip.domain.place;

import com.triple.mytrip.domain.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Place extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "place_id")
    private Long id;

    private String name;
    private String location;

    @Enumerated(value = STRING)
    private PlaceType placeType;
    private Double rating;

    @Builder
    private Place(String name, String location, PlaceType placeType) {
        this.name = name;
        this.location = location;
        this.placeType = placeType;
        this.rating = 0D;
    }
}
