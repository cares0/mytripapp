package com.triple.mytrip.web.schedule.request;

import com.triple.mytrip.domain.schedule.Schedule;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Getter @Setter
@NoArgsConstructor(access = PRIVATE)
public class ScheduleSaveRequest {

    @NotNull
    private LocalDate date;

    @NotNull
    private Integer visitOrder;

    @NotNull
    private Integer arrangeOrder;

    public Schedule toEntity() {
        return Schedule.builder()
                .date(this.date)
                .visitOrder(this.visitOrder)
                .arrangeOrder(this.arrangeOrder)
                .build();
    }
}
