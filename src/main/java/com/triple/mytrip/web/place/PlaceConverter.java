package com.triple.mytrip.web.place;

import com.triple.mytrip.domain.place.Place;
import com.triple.mytrip.domain.place.accommodation.Accommodation;
import com.triple.mytrip.domain.place.flight.Flight;
import com.triple.mytrip.domain.place.restaurant.Restaurant;
import com.triple.mytrip.domain.place.shop.Shop;
import com.triple.mytrip.domain.place.tour.Tour;
import com.triple.mytrip.web.place.form.PlaceForm;
import com.triple.mytrip.web.place.form.PlaceSubType;

import static com.triple.mytrip.web.place.form.PlaceSubType.*;

public class PlaceConverter {

    public static Place formToEntity(PlaceForm placeForm) {
        if (placeForm.getSubType() == ACCOMMODATION) {
            return new Accommodation(
                    placeForm.getName(),
                    placeForm.getDate(),
                    placeForm.getLocation(),
                    placeForm.getPlaceOrder());
        } else if (placeForm.getSubType() == FLIGHT) {
            return new Flight(
                    placeForm.getName(),
                    placeForm.getDate(),
                    placeForm.getLocation(),
                    placeForm.getPlaceOrder(),
                    placeForm.getFlightNumber(),
                    placeForm.getAirline(),
                    placeForm.getDepartureDate(),
                    placeForm.getDepartureTime(),
                    placeForm.getArrivalTime(),
                    placeForm.getDepartureAirport(),
                    placeForm.getArrivalAirport());
        } else if (placeForm.getSubType() == RESTAURANT) {
            return new Restaurant(
                    placeForm.getName(),
                    placeForm.getDate(),
                    placeForm.getLocation(),
                    placeForm.getPlaceOrder());
        } else if (placeForm.getSubType() == SHOP) {
            return new Shop(
                    placeForm.getName(),
                    placeForm.getDate(),
                    placeForm.getLocation(),
                    placeForm.getPlaceOrder());
        } else {
            return new Tour(
                    placeForm.getName(),
                    placeForm.getDate(),
                    placeForm.getLocation(),
                    placeForm.getPlaceOrder());
        }
    }
}
