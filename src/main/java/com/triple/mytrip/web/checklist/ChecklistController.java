package com.triple.mytrip.web.checklist;

import com.triple.mytrip.domain.checklist.Checklist;
import com.triple.mytrip.domain.checklist.ChecklistService;
import com.triple.mytrip.web.checklist.response.ChecklistEditResponse;
import com.triple.mytrip.web.checklist.response.ChecklistSearchResponse;
import com.triple.mytrip.web.checklist.request.ChecklistEditRequest;
import com.triple.mytrip.web.common.Result;
import com.triple.mytrip.web.exception.NoModifiedDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static com.triple.mytrip.web.checklist.ChecklistConverter.*;

@RestController
@RequiredArgsConstructor
public class ChecklistController {

    private final ChecklistService checklistService;

    @GetMapping("/checklist/{checklistId}")
    public Result<ChecklistSearchResponse> searchOne(@PathVariable Long checklistId) {
        Checklist checklist = checklistService.getOne(checklistId);
        ChecklistSearchResponse checklistSearchResponse = entityToDto(checklist);
        return new Result<>(checklistSearchResponse);
    }

    @PatchMapping("/checklist/{checklistId}")
    public Result<ChecklistEditResponse> edit(@PathVariable Long checklistId, @RequestBody ChecklistEditRequest checklistEditRequest) {
        Checklist checklist = updateEntity(checklistId, checklistEditRequest);
        ChecklistEditResponse checklistEditResponse = entityToEditResponse(checklist);
        return new Result<>(checklistEditResponse);
    }

    @DeleteMapping("/checklist/{checklistId}")
    public Result<String> delete(@PathVariable Long checklistId) {
        checklistService.delete(checklistId);
        return new Result<>("Success");
    }

    private Checklist updateEntity(Long checklistId, ChecklistEditRequest checklistEditRequest) {
        String editMemo = checklistEditRequest.getMemo();
        Checklist modifiedChecklist = null;
        if (Objects.nonNull(editMemo)) {
            modifiedChecklist = checklistService.editMemo(checklistId, editMemo);
        }
        String editName = checklistEditRequest.getName();
        if (Objects.nonNull(editName)) {
            modifiedChecklist = checklistService.editName(checklistId, editName);
        }
        Boolean editStatus = checklistEditRequest.getStatus();
        if(Objects.nonNull(editStatus)) {
            modifiedChecklist = checklistService.editCheckStatus(checklistId, editStatus);
        }

        if (Objects.isNull(modifiedChecklist)) {
            throw new NoModifiedDataException();
        } else {
            return modifiedChecklist;
        }
    }
}
