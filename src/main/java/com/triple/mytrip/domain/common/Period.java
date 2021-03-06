package com.triple.mytrip.domain.common;

import com.triple.mytrip.domain.exception.WrongDepartureDateException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Period {

    private LocalDate arrivalDate;

    private LocalDate departureDate;

    @Builder
    private Period(LocalDate arrivalDate, LocalDate departureDate) {
        if (Objects.nonNull(departureDate) && Objects.nonNull(arrivalDate)
                && departureDate.isAfter(arrivalDate)) {
            throw new WrongDepartureDateException();
        }
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
    }

    public void editArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void editDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public boolean isWithinPeriod(LocalDate date) {
        return (date.isAfter(departureDate) || date.isEqual(departureDate))
                && (date.isBefore(arrivalDate) || date.isEqual(arrivalDate));
    }
}
