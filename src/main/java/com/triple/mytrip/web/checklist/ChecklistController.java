package com.triple.mytrip.web.checklist;

import com.triple.mytrip.domain.checklist.Checklist;
import com.triple.mytrip.domain.checklist.ChecklistService;
import com.triple.mytrip.web.checklist.dto.ChecklistDto;
import com.triple.mytrip.web.common.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChecklistController {

    private final ChecklistService checklistService;

    @GetMapping("/checklist/{checklistId}")
    public Result<ChecklistDto> searchOne(@PathVariable Long checklistId) {
        Checklist checklist = checklistService.getOne(checklistId);
        ChecklistDto checklistDto = ChecklistConverter.EntityToDto(checklist);
        return new Result<>(checklistDto);
    }

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

    @DeleteMapping("/checklist/{checklistId}")
    public Result<String> delete(@PathVariable Long checklistId) {
        checklistService.delete(checklistId);
        return new Result<>("Success");
    }
}
