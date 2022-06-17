package com.triple.mytrip.web.place;

import com.triple.mytrip.domain.place.Place;
import com.triple.mytrip.web.place.form.PlaceForm;

public class PlaceConverter {

    public static Place formToEntity(PlaceForm placeForm) {
        return new Place(
                placeForm.getName(),
                placeForm.getDate(),
                placeForm.getTripCategory(),
                placeForm.getLocation(),
                placeForm.getPlaceOrder());
    }
}
