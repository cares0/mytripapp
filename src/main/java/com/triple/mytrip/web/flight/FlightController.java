package com.triple.mytrip.web.flight;


import com.triple.mytrip.domain.flight.Flight;
import com.triple.mytrip.domain.flight.FlightService;
import com.triple.mytrip.web.common.Result;
import com.triple.mytrip.web.flight.request.FlightEditRequest;
import com.triple.mytrip.web.flight.response.FlightEditResponse;
import com.triple.mytrip.web.flight.response.FlightSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @GetMapping("/flights/{flightId}")
    public FlightSearchResponse searchOne(@PathVariable Long flightId) {
        Flight flight = flightService.getOne(flightId);
        return FlightConverter.entityToSearchResponse(flight);
    }

    @PatchMapping("/flights/{flightId}")
    public FlightEditResponse edit(@PathVariable Long flightId, @RequestBody FlightEditRequest flightEditRequest) {
        Flight flight = FlightConverter.editRequestToEntity(flightEditRequest);
        Flight modified = flightService.edit(flightId, flight);
        return FlightConverter.entityToEditResponse(modified);
    }

    @DeleteMapping("/flights/{flightId}")
    public Result<String> delete(@PathVariable Long flightId) {
        flightService.delete(flightId);
        return new Result<>("success");
    }

}
