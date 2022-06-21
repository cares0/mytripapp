package com.triple.mytrip.web.checklistcategory.checklist.response;

import com.triple.mytrip.domain.checklistcategory.checklist.Checklist;
import lombok.*;

import static lombok.AccessLevel.*;

@Getter @Setter
@Builder
@AllArgsConstructor(access = PRIVATE)
public class ChecklistEditResponse {

    private Long id;
    private Boolean checkStatus;
    private String name;
    private String memo;
    private String instruction;

    public static ChecklistEditResponse toResponse(Checklist checklist) {
        return ChecklistEditResponse.builder()
                .id(checklist.getId())
                .checkStatus(checklist.getCheckStatus())
                .name(checklist.getName())
                .memo(checklist.getMemo())
                .instruction(checklist.getInstruction())
                .build();
    }

}
