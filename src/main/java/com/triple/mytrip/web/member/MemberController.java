package com.triple.mytrip.web.member;

import com.triple.mytrip.domain.member.MemberService;
import com.triple.mytrip.domain.trip.Trip;
import com.triple.mytrip.web.common.Result;
import com.triple.mytrip.web.trip.request.TripSaveRequest;
import com.triple.mytrip.web.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final ValidationUtils validationUtils;

    @PostMapping("/{memberId}/trips")
    public Result<Long> tripAdd(@PathVariable Long memberId,
                                @Validated @RequestBody TripSaveRequest tripSaveRequest,
                                BindingResult bindingResult) {
        validationUtils.validate(bindingResult);

        Trip trip = tripSaveRequest.toEntity();
        Long savedId = memberService.addTrip(memberId, trip);
        return new Result<>(savedId);
    }

}
