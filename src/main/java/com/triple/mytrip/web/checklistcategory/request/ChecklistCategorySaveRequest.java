package com.triple.mytrip.web.checklistcategory.request;

import com.triple.mytrip.domain.checklistcategory.ChecklistCategory;
import lombok.*;

import javax.validation.constraints.NotBlank;

import static lombok.AccessLevel.*;

@Getter @Setter
@NoArgsConstructor(access = PRIVATE)
public class ChecklistCategorySaveRequest {

    @NotBlank
    private String name;

    public ChecklistCategory toEntity() {
        return ChecklistCategory.builder()
                .name(this.name)
                .basicOfferStatus(false)
                .build();
    }

}
