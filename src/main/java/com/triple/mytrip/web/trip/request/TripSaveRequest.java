package com.triple.mytrip.web.trip.request;

import com.triple.mytrip.domain.trip.Trip;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

import static lombok.AccessLevel.*;

@Getter @Setter
@NoArgsConstructor(access = PRIVATE)
public class TripSaveRequest {

    @NotBlank
    private String city;

    public Trip toEntity() {
        return Trip.builder()
                .city(city)
                .title(city + " 여행")
                .build();
    }
}
