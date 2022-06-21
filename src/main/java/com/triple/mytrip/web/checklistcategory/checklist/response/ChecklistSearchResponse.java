package com.triple.mytrip.web.checklistcategory.checklist.response;

import com.triple.mytrip.domain.checklistcategory.checklist.Checklist;
import lombok.*;

import static lombok.AccessLevel.*;

@Getter @Setter
@Builder
@AllArgsConstructor(access = PRIVATE)
public class ChecklistSearchResponse {

    private Long id;
    private Boolean checkStatus;
    private Boolean basicOfferStatus;
    private String name;
    private String memo;
    private String instruction;

    public static ChecklistSearchResponse toResponse(Checklist checklist) {
        return ChecklistSearchResponse.builder()
                .id(checklist.getId())
                .checkStatus(checklist.getCheckStatus())
                .basicOfferStatus(checklist.getBasicOfferStatus())
                .name(checklist.getName())
                .memo(checklist.getMemo())
                .instruction(checklist.getInstruction())
                .build();
    }
}
