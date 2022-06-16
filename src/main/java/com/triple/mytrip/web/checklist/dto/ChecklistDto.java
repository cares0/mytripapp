package com.triple.mytrip.web.checklist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ChecklistDto {

    private Long id;
    private Boolean checkStatus;
    private Boolean basicOfferStatus;
    private String name;
    private String memo;
    private String instruction;
}
