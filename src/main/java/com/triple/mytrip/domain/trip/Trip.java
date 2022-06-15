package com.triple.mytrip.domain.trip;

import com.triple.mytrip.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Trip {

    @Id @GeneratedValue
    @Column(name = "trip_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    LocalDate arrivalDate;

    LocalDate departureDate;

    @Enumerated(value = EnumType.STRING)
    Partner partner;

    @Enumerated(value = EnumType.STRING)
    TripStyle tripStyle;

    @Column(nullable = false)
    String city;

    @Column(nullable = false)
    String title;

    public Trip(Member member, String city) {
        addMember(member);
        this.city = city;
        this.title = city + " 여행";
    }

    private void addMember(Member member) {
        this.member = member;
    }

    public void editTitle(String title) {
        this.title = title;
    }

    public void editDate(LocalDate arrivalDate, LocalDate departureDate) {
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
    }

    public void editTripStyle(String city, TripStyle tripStyle, Partner partner) {
        this.city = city;
        this.tripStyle = tripStyle;
        this.partner = partner;
    }

    public void editCity(String city) {
        this.city = city;
    }
}
