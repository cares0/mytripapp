package com.triple.mytrip.web.checklist.dto;

import com.triple.mytrip.domain.checklist.Checklist;
import com.triple.mytrip.domain.trip.Trip;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@AllArgsConstructor
@Setter @Getter
public class ChecklistCategoryDto {

    private Long id;
    private Long tripId;
    private List<ChecklistDto> checklists;
    private Boolean basicOfferStatus;
    private String name;
}
