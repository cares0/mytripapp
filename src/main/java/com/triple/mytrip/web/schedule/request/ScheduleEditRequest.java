package com.triple.mytrip.web.schedule.request;

import com.triple.mytrip.domain.schedule.Schedule;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

import static lombok.AccessLevel.*;

@Getter @Setter
@NoArgsConstructor(access = PRIVATE)
public class ScheduleEditRequest {

    private LocalTime visitTime;
    private String memo;
    private LocalDate date;
    private Integer visitOrder;
    private Integer arrangeOrder;

    public Schedule toEntity() {
        return Schedule.builder()
                .visitTime(this.visitTime)
                .memo(this.memo)
                .date(this.date)
                .visitOrder(this.visitOrder)
                .arrangeOrder(this.arrangeOrder)
                .build();
    }

}