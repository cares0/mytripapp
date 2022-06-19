package com.triple.mytrip.web.schedule.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class PlaceDto {

    private Long id;
    private String name;
    private String placeType;
    private String location;
}
