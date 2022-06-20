package com.triple.mytrip.domain.trip;

import com.triple.mytrip.domain.exception.EntityNotFoundException;
import com.triple.mytrip.domain.member.Member;
import com.triple.mytrip.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TripService {

    private final MemberRepository memberRepository;
    private final TripRepository tripRepository;

    @Transactional
    public Long save(Long memberId, Trip trip) {
        Member member = memberRepository.findById(memberId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 회원을 찾을 수 없음"));

        trip.addMember(member);
        Trip saved = tripRepository.save(trip);
        return saved.getId();
    }


}
