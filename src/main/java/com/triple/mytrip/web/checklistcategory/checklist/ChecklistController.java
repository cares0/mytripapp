package com.triple.mytrip.web.checklistcategory.checklist;

import com.triple.mytrip.domain.checklistcategory.checklist.Checklist;
import com.triple.mytrip.domain.checklistcategory.checklist.ChecklistService;
import com.triple.mytrip.web.checklistcategory.checklist.response.ChecklistEditResponse;
import com.triple.mytrip.web.checklistcategory.checklist.response.ChecklistSearchResponse;
import com.triple.mytrip.web.checklistcategory.checklist.request.ChecklistEditRequest;
import com.triple.mytrip.web.common.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChecklistController {

    private final ChecklistService checklistService;

    @GetMapping("/checklist/{checklistId}")
    public ChecklistSearchResponse checklistDetail(@PathVariable Long checklistId) {
        Checklist checklist = checklistService.getOne(checklistId);
        return ChecklistSearchResponse.toResponse(checklist);
    }

    @PatchMapping("/checklist/{checklistId}")
    public ChecklistEditResponse checklistModify(@PathVariable Long checklistId, @RequestBody ChecklistEditRequest checklistEditRequest) {
        Checklist modified = checklistEditRequest.toEntity();
        modified = checklistService.modify(checklistId, modified);
        return ChecklistEditResponse.toResponse(modified);
    }

    @DeleteMapping("/checklist/{checklistId}")
    public Result<String> checklistRemove(@PathVariable Long checklistId) {
        checklistService.remove(checklistId);
        return new Result<>("Success");
    }
}
