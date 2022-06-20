package com.triple.mytrip.domain.budget;

import com.triple.mytrip.domain.budget.budgetfile.BudgetFile;
import com.triple.mytrip.domain.trip.Trip;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Budget {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<BudgetFile> budgetFiles = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private TripCategory tripCategory;

    private Integer price;

    private LocalDate date;

    @Enumerated(value = EnumType.STRING)
    private PaymentPlan paymentPlan;

    @Column(name = "budget_order")
    private Integer order;

    private String place;

    private String content;

    @Builder
    private Budget(TripCategory tripCategory, Integer price, LocalDate date, PaymentPlan paymentPlan, Integer order, String place, String content) {
        this.tripCategory = tripCategory;
        this.price = price;
        this.date = date;
        this.paymentPlan = paymentPlan;
        this.order = order;
        this.place = place;
        this.content = content;
    }

    public void addTrip(Trip trip) {
        this.trip = trip;
    }

    public void editTripCategory(TripCategory tripCategory) {
        this.tripCategory = tripCategory;
    }

    public void editPrice(Integer price) {
        this.price = price;
    }

    public void editDate(LocalDate date) {
        this.date = date;
    }

    public void editPaymentPlan(PaymentPlan paymentPlan) {
        this.paymentPlan = paymentPlan;
    }

    public void editOrder(Integer order) {
        this.order = order;
    }

    public void editPlace(String place) {
        this.place = place;
    }

    public void editContent(String content) {
        this.content = content;
    }
}
