package com.triple.mytrip.web.trip.request;

import com.triple.mytrip.domain.trip.Partner;
import com.triple.mytrip.domain.trip.Trip;
import com.triple.mytrip.domain.trip.TripStyle;
import lombok.*;

import java.time.LocalDate;

import static lombok.AccessLevel.*;

@Getter @Setter
@NoArgsConstructor(access = PRIVATE)
public class TripEditRequest {

    private String city;
    private String title;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private Partner partner;
    private TripStyle tripStyle;

    public Trip toEntity() {
        return Trip.builder()
                .city(this.city)
                .title(this.title)
                .arrivalDate(this.arrivalDate)
                .departureDate(this.departureDate)
                .partner(this.partner)
                .tripStyle(this.tripStyle)
                .build();
    }

}
