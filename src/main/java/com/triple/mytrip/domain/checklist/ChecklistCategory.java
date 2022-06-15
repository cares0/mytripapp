package com.triple.mytrip.domain.checklist;

import com.triple.mytrip.domain.exception.NonEditableEntityException;
import com.triple.mytrip.domain.trip.Trip;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChecklistCategory {

    @Id @GeneratedValue
    @Column(name = "checklist_category_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip;
    
    @OneToMany(mappedBy = "category")
    private List<Checklist> checklists;

    private Boolean basicOfferStatus;
    private String name;

    public ChecklistCategory(Trip trip, String name) {
        this.trip = trip;
        this.basicOfferStatus = false;
        this.name = name;
    }

    private void addTrip(Trip trip) {
        this.trip = trip;
    }

    public void editName(String name) {
        if (!this.basicOfferStatus) {
            this.name = name;
        } else {
            throw new NonEditableEntityException("기본 제공 체크리스트는 이름 수정 불가능");
        }
    }
}