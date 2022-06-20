package com.triple.mytrip.web.checklistcategory.checklist.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ChecklistSearchResponse {

    private Long id;
    private Boolean checkStatus;
    private Boolean basicOfferStatus;
    private String name;
    private String memo;
    private String instruction;
}
