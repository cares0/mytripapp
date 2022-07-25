package com.triple.mytrip.web.trip.request;

import com.triple.mytrip.domain.trip.Trip;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Getter @Setter
@NoArgsConstructor(access = PRIVATE)
public class TripSaveRequest {

    @NotBlank
    private String city;

    @NotNull
    private LocalDate arrivalDate;

    @NotNull
    private LocalDate departureDate;

    public Trip toEntity() {
        return Trip.builder()
                .city(city)
                .arrivalDate(arrivalDate)
                .departureDate(departureDate)
                .title(city + " 여행")
                .build();
    }
}
