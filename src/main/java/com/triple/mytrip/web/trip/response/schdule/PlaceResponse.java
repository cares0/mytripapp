package com.triple.mytrip.web.trip.response.schdule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class PlaceResponse {

    private Long id;
    private String name;
    private String placeType;
    private String location;
}
