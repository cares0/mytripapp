package com.triple.mytrip.web.checklistcategory.response;

import com.triple.mytrip.domain.checklistcategory.ChecklistCategory;
import com.triple.mytrip.web.checklistcategory.checklist.response.ChecklistSearchResponse;
import lombok.*;
import org.hibernate.LazyInitializationException;

import java.util.List;
import java.util.stream.Collectors;

@Setter @Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChecklistCategorySearchResponse {

    private Long id;
    private Boolean basicOfferStatus;
    private String name;
    private List<ChecklistSearchResponse> checklists;

    public static ChecklistCategorySearchResponse toResponse(ChecklistCategory checklistCategory) {
        List<ChecklistSearchResponse> checklistSearchResponses =
                getChecklistSearchResponses(checklistCategory);

        return ChecklistCategorySearchResponse.builder()
                .id(checklistCategory.getId())
                .basicOfferStatus(checklistCategory.getBasicOfferStatus())
                .name(checklistCategory.getName())
                .checklists(checklistSearchResponses)
                .build();
    }

    private static List<ChecklistSearchResponse> getChecklistSearchResponses(ChecklistCategory checklistCategory) {
        try {
            return checklistCategory.getChecklists().stream().map((checklist ->
                    ChecklistSearchResponse.toResponse(checklist))).collect(Collectors.toList());
        } catch (LazyInitializationException e) {
            return null;
        }
    }

}

