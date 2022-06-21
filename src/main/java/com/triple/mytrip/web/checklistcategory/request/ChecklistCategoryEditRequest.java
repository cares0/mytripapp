package com.triple.mytrip.web.checklistcategory.request;

import com.triple.mytrip.domain.checklistcategory.ChecklistCategory;
import lombok.*;

import static lombok.AccessLevel.*;

@Getter @Setter
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public class ChecklistCategoryEditRequest {

    private String name;

    public ChecklistCategory toEntity() {
        return ChecklistCategory.builder()
                .name(this.name)
                .build();
    }
}
