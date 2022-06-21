package com.triple.mytrip.domain.checklistcategory;

import com.triple.mytrip.domain.checklistcategory.checklist.Checklist;
import com.triple.mytrip.domain.exception.NonEditableEntityException;
import com.triple.mytrip.domain.trip.Trip;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChecklistCategory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checklist_category_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Checklist> checklists = new ArrayList<>();

    private Boolean basicOfferStatus;
    private String name;

    @Builder
    private ChecklistCategory(String name, Boolean basicOfferStatus) {
        this.basicOfferStatus = basicOfferStatus;
        this.name = name;
    }

    public void addTrip(Trip trip) {
        this.trip = trip;
        trip.getChecklistCategories().add(this);
    }

    public void editName(String name) {
        if (!this.basicOfferStatus) {
            this.name = name;
        } else {
            throw new NonEditableEntityException("기본 제공 체크리스트는 이름 수정 불가능");
        }
    }
}
