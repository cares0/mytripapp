package com.triple.mytrip.domain.budget;

import com.triple.mytrip.domain.budget.budgetfile.BudgetFile;
import com.triple.mytrip.domain.common.TripCategory;
import com.triple.mytrip.domain.trip.Trip;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Budget {

    @Id @GeneratedValue
    @Column(name = "budget_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @OneToMany(mappedBy = "budget")
    private List<BudgetFile> budgetFiles;

    @Enumerated(value = EnumType.STRING)
    private TripCategory tripCategory;

    private Integer price;

    private LocalDate date;

    @Enumerated(value = EnumType.STRING)
    private PaymentPlan paymentPlan;

    private Integer order;

    private String place;

    private String content;

    public Budget(TripCategory tripCategory, Integer price, LocalDate date, PaymentPlan paymentPlan, Integer order, String place) {
        this.tripCategory = tripCategory;
        this.price = price;
        this.date = date;
        this.paymentPlan = paymentPlan;

        // 데이터 삽입 시 순서 계산 로직 나중에 개발
        addOrder(order);

        this.place = place;
    }

    public void addOrder(Integer order) {
        this.order = order;
    }

    public void editDate(LocalDate date) {
        this.date = date;
    }

    public void editAll(TripCategory tripCategory, Integer price, LocalDate date,
                        PaymentPlan paymentPlan, String place, String content) {
        // 나중에 파일 수정하는 로직 개발

        this.tripCategory = tripCategory;
        this.price = price;
        this.date = date;
        this.paymentPlan = paymentPlan;
        this.place = place;
        this.content = content;
    }


}
