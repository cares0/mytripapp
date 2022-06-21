package com.triple.mytrip.web.place.response;

import com.triple.mytrip.domain.place.Place;
import com.triple.mytrip.domain.place.PlaceType;
import lombok.*;

import java.util.Objects;

import static lombok.AccessLevel.*;

@Getter @Setter
@Builder
@AllArgsConstructor(access = PRIVATE)
public class PlaceSearchResponse {

    private Long id;
    private String name;
    private String location;
    private String placeType;
    private Double rating;

    public static PlaceSearchResponse toResponse(Place place) {
        String placeType = Objects.isNull(place.getPlaceType()) ?
                null : place.getPlaceType().getKorName();

        return PlaceSearchResponse.builder()
                .id(place.getId())
                .name(place.getName())
                .location(place.getLocation())
                .placeType(placeType)
                .rating(place.getRating())
                .build();
    }
}
