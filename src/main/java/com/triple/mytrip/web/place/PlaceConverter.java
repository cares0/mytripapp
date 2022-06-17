package com.triple.mytrip.web.place;

import com.triple.mytrip.domain.place.Place;
import com.triple.mytrip.domain.place.accommodation.Accommodation;
import com.triple.mytrip.domain.place.flight.Flight;
import com.triple.mytrip.domain.place.restaurant.Restaurant;
import com.triple.mytrip.domain.place.shop.Shop;
import com.triple.mytrip.domain.place.tour.Tour;
import com.triple.mytrip.web.place.form.PlaceForm;

public class PlaceConverter {

    public static Place formToEntity(PlaceForm placeForm, String placeType) {
        if (placeType.equals("accommodation")) {
            return new Accommodation(
                    placeForm.getName(),
                    placeForm.getDate(),
                    placeForm.getLocation(),
                    placeForm.getPlaceOrder());
        } else if (placeType.equals("flight")) {
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
        } else if (placeType.equals("restaurant")) {
            return new Restaurant(
                    placeForm.getName(),
                    placeForm.getDate(),
                    placeForm.getLocation(),
                    placeForm.getPlaceOrder());
        } else if (placeType.equals("shop")) {
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
