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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDate arrivalDate;

    private LocalDate departureDate;

    @Enumerated(value = EnumType.STRING)
    private Partner partner;

    @Enumerated(value = EnumType.STRING)
    private TripStyle tripStyle;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String title;

    /**
     * 조회용
     */
    public Trip(Long id) {
        this.id = id;
    }

    public Trip(String city) {
        this.city = city;
        this.title = city + " 여행";
    }

    public void addMember(Member member) {
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
