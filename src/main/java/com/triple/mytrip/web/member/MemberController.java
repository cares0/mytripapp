package com.triple.mytrip.web.member;

import com.triple.mytrip.domain.trip.Trip;
import com.triple.mytrip.domain.trip.TripService;
import com.triple.mytrip.web.common.Result;
import com.triple.mytrip.web.trip.request.TripSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final TripService tripService;

    @PostMapping("/members/{memberId}/trips")
    public Result<Long> save(@PathVariable Long memberId, @RequestBody TripSaveRequest tripSaveRequest) {
        Trip trip = new Trip(tripSaveRequest.getCity());
        Long savedId = tripService.save(memberId, trip);
        return new Result<>(savedId);
    }

}
