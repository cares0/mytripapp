package com.triple.mytrip.domain.checklistcategory;

import com.triple.mytrip.domain.checklistcategory.checklist.Checklist;
import com.triple.mytrip.domain.common.BaseEntity;
import com.triple.mytrip.domain.exception.NonEditableEntityException;
import com.triple.mytrip.domain.trip.Trip;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class ChecklistCategory extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "checklist_category_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @OneToMany(mappedBy = "category", cascade = REMOVE, orphanRemoval = true)
    private List<Checklist> checklists = new ArrayList<>();

    @Column(nullable = false)
    private Boolean basicOfferStatus;

    @Column(nullable = false)
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
        if (Objects.nonNull(basicOfferStatus) && !this.basicOfferStatus) {
            this.name = name;
        } else {
            throw new NonEditableEntityException("기본 제공 체크리스트는 이름 수정 불가능");
        }
    }
}
