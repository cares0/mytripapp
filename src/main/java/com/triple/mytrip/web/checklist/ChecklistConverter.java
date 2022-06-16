package com.triple.mytrip.web.checklist;

import com.triple.mytrip.domain.checklist.Checklist;
import com.triple.mytrip.web.checklist.dto.ChecklistDto;

public class ChecklistConverter {


    public static ChecklistDto EntityToDto(Checklist checklist) {
        return new ChecklistDto(
                checklist.getId(),
                checklist.getCheckStatus(),
                checklist.getBasicOfferStatus(),
                checklist.getName(),
                checklist.getMemo(),
                checklist.getInstruction());
    }
}
