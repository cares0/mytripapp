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
    public FlightSearchResponse flightDetail(@PathVariable Long flightId) {
        Flight flight = flightService.getOne(flightId);
        return FlightSearchResponse.toResponse(flight);
    }

    @PatchMapping("/flights/{flightId}")
    public FlightEditResponse flightModify(@PathVariable Long flightId, @RequestBody FlightEditRequest flightEditRequest) {
        Flight modified = flightEditRequest.toEntity();
        modified = flightService.modify(flightId, modified);
        return FlightEditResponse.toResponse(modified);
    }

    @DeleteMapping("/flights/{flightId}")
    public Result<Long> delete(@PathVariable Long flightId) {
        flightService.remove(flightId);
        return new Result<>(flightId);
    }

}
