package com.triple.mytrip.domain.trip;

import com.triple.mytrip.domain.exception.EntityNotFoundException;
import com.triple.mytrip.domain.member.Member;
import com.triple.mytrip.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;

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

    @Transactional
    public Trip edit(Long tripId, Trip modified) {
        Trip original = tripRepository.findById(tripId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 여행을 찾을 수 없음"));

        update(original, modified);
        return original;
    }

    private void update(Trip original, Trip modified) {
        String city = modified.getCity();
        if (Objects.nonNull(city)) {
            original.editCity(city);
        }
        String title = modified.getTitle();
        if (Objects.nonNull(title)) {
            original.editTitle(title);
        }
        LocalDate arrivalDate = modified.getArrivalDate();
        if (Objects.nonNull(arrivalDate)) {
            original.editArrivalDate(arrivalDate);
        }
        LocalDate departureDate = modified.getDepartureDate();
        if (Objects.nonNull(departureDate)) {
            original.editDepartureDate(departureDate);
        }
        Partner partner = modified.getPartner();
        if (Objects.nonNull(partner)) {
            original.editPartner(partner);
        }
        TripStyle tripStyle = modified.getTripStyle();
        if (Objects.nonNull(tripStyle)) {
            original.editTripStyle(tripStyle);
        }
    }


}
