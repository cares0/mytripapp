package com.triple.mytrip.web.budget.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class BudgetFileDto {

    private Long id;
    private String oriName;
    private String fileName;
}
