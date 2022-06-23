package com.triple.mytrip.web.trip.response;

import com.triple.mytrip.domain.trip.Trip;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

import static lombok.AccessLevel.PRIVATE;

@Getter @Setter
@Builder
@AllArgsConstructor(access = PRIVATE)
public class TripEditResponse {

    private Long id;
    private String city;
    private String title;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private String partner;
    private String tripStyle;

    public static TripEditResponse toResponse(Trip trip) {
        String partner = Objects.isNull(trip.getPartner()) ?
                null : trip.getPartner().getKorName();

        String tripStyle = Objects.isNull(trip.getTripStyle()) ?
                null : trip.getTripStyle().getKorName();

        return TripEditResponse.builder()
                .id(trip.getId())
                .city(trip.getCity())
                .title(trip.getTitle())
                .arrivalDate(trip.getPeriod().getArrivalDate())
                .departureDate(trip.getPeriod().getDepartureDate())
                .partner(partner)
                .tripStyle(tripStyle)
                .build();
    }
}