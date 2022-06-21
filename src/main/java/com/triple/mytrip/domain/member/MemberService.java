package com.triple.mytrip.domain.member;

import com.triple.mytrip.domain.exception.EntityNotFoundException;
import com.triple.mytrip.domain.trip.Trip;
import com.triple.mytrip.domain.trip.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final TripRepository tripRepository;

    public Long addTrip(Long memberId, Trip trip) {
        Member member = memberRepository.findById(memberId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 회원을 찾을 수 없음"));
        trip.addMember(member);
        Trip saved = tripRepository.save(trip);
        return saved.getId();
    }
}
