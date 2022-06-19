package com.triple.mytrip.web.schedule.flight;


import com.triple.mytrip.domain.schedule.flight.Flight;
import com.triple.mytrip.domain.schedule.flight.FlightService;
import com.triple.mytrip.web.schedule.flight.request.FlightEditRequest;
import com.triple.mytrip.web.schedule.flight.response.FlightEditResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @PatchMapping("/flights/{flightId}")
    public FlightEditResponse edit(@PathVariable Long flightId, @RequestBody FlightEditRequest flightEditRequest) {

        Flight flight = FlightConverter.editRequestToEntity(flightEditRequest);

        Flight modified = flightService.edit(flightId, flight);

        FlightEditResponse flightEditResponse = FlightConverter.entityToEditResponse(modified);

        return flightEditResponse;
    }


}
