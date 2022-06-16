package com.triple.mytrip.web.checklist;

import com.triple.mytrip.domain.checklist.ChecklistService;
import com.triple.mytrip.web.common.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChecklistController {

    private final ChecklistService checklistService;

    @PatchMapping("/checklist/{checklistId}/edit-name")
    public Result<String> editName(@PathVariable Long checklistId, @RequestBody String name) {
        checklistService.editName(checklistId, name);
        return new Result<>("Success");
    }

    @PatchMapping("/checklist/{checklistId}/edit-status")
    public Result<String> editStatus(@PathVariable Long checklistId) {
        checklistService.editCheckStatus(checklistId);
        return new Result<>("Success");
    }

    @PatchMapping("/checklist/{checklistId}/edit-memo")
    public Result<String> editMemo(@PathVariable Long checklistId, @RequestBody String memo) {
        checklistService.editMemo(checklistId, memo);
        return new Result<>("Success");
    }



}
