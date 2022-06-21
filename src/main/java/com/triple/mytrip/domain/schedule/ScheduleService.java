package com.triple.mytrip.domain.schedule;

import com.triple.mytrip.domain.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public Schedule getOneWithAll(Long scheduleId) {
        return scheduleRepository.findByIdWithAll(scheduleId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 일정을 찾을 수 없음"));
    }

    public Schedule modify(Long scheduleId, Schedule modified) {
        Schedule original = findSchedule(scheduleId);
        update(original, modified);
        return original;
    }

    public void remove(Long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }

    private Schedule findSchedule(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 일정을 찾을 수 없음"));
    }

    private void update(Schedule original, Schedule modified) {
        LocalTime visitTime = modified.getVisitTime();
        if (Objects.nonNull(visitTime)) {
            original.editVisitTime(visitTime);
        }
        String memo = modified.getMemo();
        if (Objects.nonNull(memo)) {
            original.editMemo(memo);
        }
        LocalDate date = modified.getDate();
        if (Objects.nonNull(date)) {
            original.editDate(date);
        }
        Integer visitOrder = modified.getVisitOrder();
        if (Objects.nonNull(visitOrder)) {
            original.editVisitOrder(visitOrder);
        }
        Integer arrangeOrder = modified.getArrangeOrder();
        if (Objects.nonNull(arrangeOrder)) {
            original.editArrangeOrder(arrangeOrder);
        }
    }

}
