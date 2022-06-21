package com.triple.mytrip.web.checklistcategory.request;

import com.triple.mytrip.domain.checklistcategory.ChecklistCategory;
import lombok.*;

import static lombok.AccessLevel.*;

@Getter @Setter
@Builder
@AllArgsConstructor(access = PRIVATE)
public class ChecklistCategorySaveRequest {

    private String name;

    public ChecklistCategory toEntity() {
        return ChecklistCategory.builder()
                .name(this.name)
                .basicOfferStatus(false)
                .build();
    }

}
