package com.triple.mytrip.web.checklist;

import com.triple.mytrip.domain.checklist.Checklist;
import com.triple.mytrip.web.checklist.dto.ChecklistDto;

public class ChecklistConverter {


    public static ChecklistDto EntityToDto(Checklist checklist) {
        return new ChecklistDto(checklist.getMemo(), checklist.getInstruction());
    }
}
