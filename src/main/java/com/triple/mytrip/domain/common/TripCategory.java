package com.triple.mytrip.domain.common;

public enum TripCategory {

    ACCOMMODATIONS("숙박"),
    FLIGHT("항공"),
    TRAFFIC("교통"),
    TOUR("관광"),
    FOOD("식비"),
    SHOPPING("쇼핑"),
    ETC("기타");

    private final String budgetCategory;

    TripCategory(String budgetCategory) {
        this.budgetCategory = budgetCategory;
    }

    public String getBudgetCategory() {
        return budgetCategory;
    }
}
