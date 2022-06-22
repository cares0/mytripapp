package com.triple.mytrip.domain.trip;

import com.triple.mytrip.domain.budget.Budget;
import com.triple.mytrip.domain.checklistcategory.ChecklistCategory;
import com.triple.mytrip.domain.common.Period;
import com.triple.mytrip.domain.member.Member;
import com.triple.mytrip.domain.schedule.Schedule;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Trip {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "trip_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "trip", cascade = REMOVE, orphanRemoval = true)
    private List<Budget> budgets = new ArrayList<>();

    @OneToMany(mappedBy = "trip", cascade = REMOVE, orphanRemoval = true)
    private List<ChecklistCategory> checklistCategories = new ArrayList<>();

    @OneToMany(mappedBy = "trip", cascade = REMOVE, orphanRemoval = true)
    private List<Schedule> schedules = new ArrayList<>();

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String title;

    @Embedded
    private Period period;

    @Enumerated(value = STRING)
    private Partner partner;

    @Enumerated(value = STRING)
    private TripStyle tripStyle;

    @Builder
    public Trip(String city, String title, LocalDate arrivalDate, LocalDate departureDate, Partner partner, TripStyle tripStyle) {
        this.city = city;
        this.title = title;
        this.period = Period.builder().arrivalDate(arrivalDate).departureDate(departureDate).build();
        this.partner = partner;
        this.tripStyle = tripStyle;
    }

    public void addMember(Member member) {
        this.member = member;
    }

    public void addPeriod(Period period) {
        this.period = period;
    }

    public void editCity(String city) {
        this.city = city;
    }

    public void editTitle(String title) {
        this.title = title;
    }

    public void editArrivalDate(LocalDate arrivalDate) {
        this.period.editArrivalDate(arrivalDate);
    }

    public void editDepartureDate(LocalDate departureDate) {
        this.period.editDepartureDate(departureDate);
    }

    public void editPartner(Partner partner) {
        this.partner = partner;
    }

    public void editTripStyle(TripStyle tripStyle) {
        this.tripStyle = tripStyle;
    }
}
