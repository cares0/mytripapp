package com.triple.mytrip.web.checklistcategory.checklist.request;

import com.triple.mytrip.domain.checklistcategory.checklist.Checklist;
import lombok.*;

import javax.validation.constraints.NotBlank;

import static lombok.AccessLevel.PRIVATE;

@Getter @Setter
@NoArgsConstructor(access = PRIVATE)
public class ChecklistSaveRequest {

    @NotBlank
    String name;

    public Checklist toEntity() {
        return Checklist.builder()
                .name(this.name)
                .basicOfferStatus(false)
                .checkStatus(false)
                .build();
    }
}
