package com.triple.mytrip.web.place.response;

import com.triple.mytrip.domain.place.Place;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter @Setter
public class PlaceSearchResponse {

    private Long id;
    private String name;
    private String placeType;
    private String location;
    private Double rating;

    @Builder
    public PlaceSearchResponse(Long id, String name, String placeType, String location, Double rating) {
        this.id = id;
        this.name = name;
        this.placeType = placeType;
        this.location = location;
        this.rating = rating;
    }

    public static PlaceSearchResponse toResponse(Place place) {
        if (Objects.isNull(place)) {
            return null;
        }
        return PlaceSearchResponse.builder()
                .id(place.getId())
                .name(place.getName())
                .placeType(place.getPlaceType().getKorName())
                .location(place.getLocation())
                .rating(place.getRating())
                .build();
    }
}
