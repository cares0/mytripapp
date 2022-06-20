package com.triple.mytrip.web.checklistcategory.response;

import com.triple.mytrip.web.checklistcategory.checklist.response.ChecklistSearchResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter @Getter
public class ChecklistCategorySearchResponse {

    private Long id;
    private Boolean basicOfferStatus;
    private String name;

    public ChecklistCategorySearchResponse(Long id, Boolean basicOfferStatus, String name) {
        this.id = id;
        this.basicOfferStatus = basicOfferStatus;
        this.name = name;
    }

    private List<ChecklistSearchResponse> checklists;

}
