package com.triple.mytrip.web.checklist.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ChecklistEditRequest {

    private String name;
    private Boolean status;
    private String memo;
}
