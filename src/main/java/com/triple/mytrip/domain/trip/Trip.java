package com.triple.mytrip.domain.trip;

import com.triple.mytrip.domain.member.Member;
import com.triple.mytrip.domain.schedule.Schedule;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "trip")
    private List<Schedule> schedules = new ArrayList<>();

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String title;

    private LocalDate arrivalDate;

    private LocalDate departureDate;

    @Enumerated(value = EnumType.STRING)
    private Partner partner;

    @Enumerated(value = EnumType.STRING)
    private TripStyle tripStyle;

    /**
     * 조회용
     */
    public Trip(Long id) {
        this.id = id;
    }

    /**
     * 저장용
     */
    public Trip(String city) {
        this.city = city;
        this.title = city + " 여행";
    }

    /**
     * 수정용
     */
    public Trip(String city, String title, LocalDate arrivalDate,
                LocalDate departureDate, Partner partner, TripStyle tripStyle) {
        this.city = city;
        this.title = title;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.partner = partner;
        this.tripStyle = tripStyle;
    }

    public void addMember(Member member) {
        this.member = member;
    }

    public void editCity(String city) {
        this.city = city;
    }

    public void editTitle(String title) {
        this.title = title;
    }

    public void editArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void editDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public void editPartner(Partner partner) {
        this.partner = partner;
    }

    public void editTripStyle(TripStyle tripStyle) {
        this.tripStyle = tripStyle;
    }
}
