package com.triple.mytrip.domain.checklist;

import com.triple.mytrip.domain.checklist.category.ChecklistCategory;
import com.triple.mytrip.domain.exception.NonEditableEntityException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    private String name;
    private Boolean checkStatus;
    private Boolean basicOfferStatus;
    private String memo;
    private String instruction;

    public Checklist(String name) {
        this.name = name;
        checkStatus = false;
        basicOfferStatus = false;
    }

    public void addCategory(ChecklistCategory category) {
        this.category = category;
    }

    public void editName(String name) {
        if(!basicOfferStatus) {
            this.name = name;
        } else {
            throw new NonEditableEntityException("기본 제공 체크리스트는 이름 수정 불가");
        }
    }

    public void editMemo(String memo) {
        this.memo = memo;
    }

    public void editCheckStatus() {
        this.checkStatus = !this.checkStatus;
    }

}
