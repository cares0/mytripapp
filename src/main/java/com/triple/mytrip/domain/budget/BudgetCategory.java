package com.triple.mytrip.domain.budget;

public enum BudgetCategory {

    ACCOMMODATIONS("숙박"),
    FLIGHT("항공"),
    TRAFFIC("교통"),
    TOUR("관광"),
    FOOD("식비"),
    SHOPPING("쇼핑"),
    ETC("기타");

    private final String korName;

    BudgetCategory(String korName) {
        this.korName = korName;
    }

    public String getKorName() {
        return korName;
    }
}
