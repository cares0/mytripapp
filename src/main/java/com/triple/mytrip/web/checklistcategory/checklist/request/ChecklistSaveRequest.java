package com.triple.mytrip.web.checklistcategory.checklist.request;

import com.triple.mytrip.domain.checklistcategory.checklist.Checklist;
import lombok.*;

import static lombok.AccessLevel.PRIVATE;

@Getter @Setter
@Builder
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
public class ChecklistSaveRequest {

    String name;

    public Checklist toEntity() {
        return Checklist.builder()
                .name(this.name)
                .basicOfferStatus(false)
                .checkStatus(false)
                .build();
    }
}
