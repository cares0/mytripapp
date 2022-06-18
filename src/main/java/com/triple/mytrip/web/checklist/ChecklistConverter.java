package com.triple.mytrip.web.checklist;

import com.triple.mytrip.domain.checklist.Checklist;
import com.triple.mytrip.web.checklist.request.ChecklistEditRequest;
import com.triple.mytrip.web.checklist.response.ChecklistEditResponse;
import com.triple.mytrip.web.checklist.response.ChecklistSearchResponse;

public class ChecklistConverter {

    public static ChecklistSearchResponse entityToDto(Checklist checklist) {
        return new ChecklistSearchResponse(
                checklist.getId(),
                checklist.getCheckStatus(),
                checklist.getBasicOfferStatus(),
                checklist.getName(),
                checklist.getMemo(),
                checklist.getInstruction());
    }

    public static Checklist editResponseToEntity(ChecklistEditRequest checklistEditRequest) {
        return new Checklist(
                checklistEditRequest.getName(),
                checklistEditRequest.getMemo(),
                checklistEditRequest.getCheckStatus()
        );
    }

    public static ChecklistEditResponse entityToEditResponse(Checklist checklist) {
        return new ChecklistEditResponse(
                checklist.getId(),
                checklist.getCheckStatus(),
                checklist.getName(),
                checklist.getMemo(),
                checklist.getInstruction()
        );
    }
}
