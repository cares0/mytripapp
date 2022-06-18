package com.triple.mytrip.web.checklist.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter @Getter
public class ChecklistCategoryDto {

    private Long id;
    private Long tripId;
    private List<ChecklistSearchResponse> checklists;
    private Boolean basicOfferStatus;
    private String name;
}
