package com.triple.mytrip.web.checklist.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ChecklistEditResponse {

    private Long id;
    private Boolean checkStatus;
    private String name;
    private String memo;
    private String instruction;

}
