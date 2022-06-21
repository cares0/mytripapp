package com.triple.mytrip.web.checklistcategory.response;

import com.triple.mytrip.domain.checklistcategory.ChecklistCategory;
import lombok.*;

import static lombok.AccessLevel.*;

@Getter @Setter
@Builder
@AllArgsConstructor(access = PRIVATE)
public class ChecklistCategoryEditResponse {

    private Long id;
    private String name;

    public static ChecklistCategoryEditResponse toResponse(ChecklistCategory checklistCategory) {
        return ChecklistCategoryEditResponse.builder()
                .id(checklistCategory.getId())
                .name(checklistCategory.getName())
                .build();
    }
}
