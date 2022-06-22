package com.triple.mytrip.domain.budget;

import com.triple.mytrip.domain.budget.budgetfile.BudgetFile;
import com.triple.mytrip.domain.common.BaseEntity;
import com.triple.mytrip.domain.trip.Trip;
import lombok.*;

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
public class Budget extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "budget_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @OneToMany(mappedBy = "budget", cascade = REMOVE, orphanRemoval = true)
    private List<BudgetFile> budgetFiles = new ArrayList<>();

    @Enumerated(value = STRING)
    @Column(nullable = false)
    private BudgetCategory budgetCategory;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(value = STRING)
    @Column(nullable = false)
    private PaymentPlan paymentPlan;

    @Column(name = "budget_order", nullable = false)
    private Integer order;

    private String place;

    @Column(nullable = false)
    private String content;

    @Builder
    private Budget(BudgetCategory budgetCategory, Integer price, LocalDate date, PaymentPlan paymentPlan, Integer order, String place, String content) {
        this.budgetCategory = budgetCategory;
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

    public void editBudgetCategory(BudgetCategory budgetCategory) {
        this.budgetCategory = budgetCategory;
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
