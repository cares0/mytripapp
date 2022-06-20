package com.triple.mytrip.web.checklistcategory.response;

import com.triple.mytrip.web.checklistcategory.checklist.response.ChecklistSearchResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter @Getter
public class ChecklistCategorySearchResponse {

    private Long id;
    private Long tripId;
    private List<ChecklistSearchResponse> checklists;
    private Boolean basicOfferStatus;
    private String name;
}
