package com.triple.mytrip.web.checklist;

import com.triple.mytrip.domain.checklist.Checklist;
import com.triple.mytrip.domain.checklist.ChecklistService;
import com.triple.mytrip.web.checklist.response.ChecklistEditResponse;
import com.triple.mytrip.web.checklist.response.ChecklistSearchResponse;
import com.triple.mytrip.web.checklist.request.ChecklistEditRequest;
import com.triple.mytrip.web.common.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public ChecklistEditResponse edit(@PathVariable Long checklistId, @RequestBody ChecklistEditRequest checklistEditRequest) {
        Checklist modifiedChecklist = editResponseToEntity(checklistEditRequest);

        modifiedChecklist = checklistService.edit(checklistId, modifiedChecklist);

        ChecklistEditResponse checklistEditResponse = entityToEditResponse(modifiedChecklist);

        return checklistEditResponse;
    }


    @DeleteMapping("/checklist/{checklistId}")
    public Result<String> delete(@PathVariable Long checklistId) {
        checklistService.delete(checklistId);
        return new Result<>("Success");
    }
}
