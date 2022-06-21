package com.triple.mytrip.web.schedule.response;

import com.triple.mytrip.domain.schedule.Schedule;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

import static lombok.AccessLevel.*;

@Getter @Setter
@Builder
@AllArgsConstructor(access = PRIVATE)
public class ScheduleEditResponse {

    private Long id;
    private LocalTime visitTime;
    private String memo;
    private LocalDate date;
    private Integer visitOrder;
    private Integer arrangeOrder;

    public static ScheduleEditResponse toResponse(Schedule schedule) {
        return ScheduleEditResponse.builder()
                .id(schedule.getId())
                .visitTime(schedule.getVisitTime())
                .memo(schedule.getMemo())
                .date(schedule.getDate())
                .visitOrder(schedule.getVisitOrder())
                .arrangeOrder(schedule.getArrangeOrder())
                .build();
    }

}
