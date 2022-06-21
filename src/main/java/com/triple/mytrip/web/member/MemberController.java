package com.triple.mytrip.web.member;

import com.triple.mytrip.domain.member.MemberService;
import com.triple.mytrip.domain.trip.Trip;
import com.triple.mytrip.domain.trip.TripService;
import com.triple.mytrip.web.common.Result;
import com.triple.mytrip.web.trip.request.TripSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members/{memberId}/trips")
    public Result<Long> save(@PathVariable Long memberId, @RequestBody TripSaveRequest tripSaveRequest) {
        Trip trip = tripSaveRequest.toEntity();
        Long savedId = memberService.addTrip(memberId, trip);
        return new Result<>(savedId);
    }

}
