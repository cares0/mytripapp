package com.triple.mytrip.web.checklistcategory.checklist.request;

import com.triple.mytrip.domain.checklistcategory.checklist.Checklist;
import lombok.*;

import static lombok.AccessLevel.*;

@Getter @Setter
@Builder
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
public class ChecklistEditRequest {

    private String name;
    private Boolean checkStatus;
    private String memo;

    public Checklist toEntity() {
        return Checklist.builder()
                .name(this.name)
                .checkStatus(this.checkStatus)
                .memo(this.memo)
                .build();
    }
}
