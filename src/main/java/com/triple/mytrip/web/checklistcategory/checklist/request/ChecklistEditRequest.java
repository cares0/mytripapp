package com.triple.mytrip.web.checklistcategory.checklist.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ChecklistEditRequest {

    private String name;
    private Boolean checkStatus;
    private String memo;
}
