package com.triple.mytrip.domain.checklistcategory.checklist;

import com.triple.mytrip.domain.checklistcategory.ChecklistCategory;
import com.triple.mytrip.domain.exception.NonEditableEntityException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Checklist {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checklist_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "checklist_category_id")
    private ChecklistCategory category;

    private Boolean checkStatus;
    private String name;
    private Boolean basicOfferStatus;
    private String memo;
    private String instruction;

    @Builder
    private Checklist(String name, Boolean checkStatus, Boolean basicOfferStatus, String memo, String instruction) {
        this.name = name;
        this.checkStatus = checkStatus;
        this.basicOfferStatus = basicOfferStatus;
        this.memo = memo;
        this.instruction = instruction;
    }

    public void addCategory(ChecklistCategory category) {
        this.category = category;
        category.getChecklists().add(this);
    }

    public void editName(String name) {
        if(Objects.nonNull(basicOfferStatus) && !basicOfferStatus) {
            this.name = name;
        } else {
            throw new NonEditableEntityException("기본 제공 체크리스트는 이름 수정 불가");
        }
    }

    public void editMemo(String memo) {
        this.memo = memo;
    }

    public void editCheckStatus(Boolean checkStatus) {
        this.checkStatus = checkStatus;
    }

}
