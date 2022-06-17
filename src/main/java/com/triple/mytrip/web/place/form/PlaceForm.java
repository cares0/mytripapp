package com.triple.mytrip.web.place.form;

import com.triple.mytrip.domain.common.TripCategory;
import com.triple.mytrip.domain.trip.Trip;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

import static javax.persistence.FetchType.LAZY;

@Getter @Setter
public class PlaceForm {

    private String name;
    private LocalDate date;
    private TripCategory tripCategory;
    private String location;
    private Integer placeOrder;

}

