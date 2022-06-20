package com.triple.mytrip.web.trip;

import com.triple.mytrip.domain.trip.Trip;
import com.triple.mytrip.web.trip.request.TripEditRequest;
import com.triple.mytrip.web.trip.response.TripEditResponse;

import java.util.List;

public class TripConverter {

    public static Trip editRequestToEntity(TripEditRequest tripEditRequest) {
        return new Trip(
                tripEditRequest.getCity(),
                tripEditRequest.getTitle(),
                tripEditRequest.getArrivalDate(),
                tripEditRequest.getDepartureDate(),
                tripEditRequest.getPartner(),
                tripEditRequest.getTripStyle()
        );
    }

    public static TripEditResponse entityToEditResponse(Trip modified) {
        return new TripEditResponse(
                modified.getId(),
                modified.getCity(),
                modified.getTitle(),
                modified.getArrivalDate(),
                modified.getDepartureDate(),
                modified.getPartner().getKorName(),
                modified.getTripStyle().getKorName()
        );
    }

}
