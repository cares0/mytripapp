package com.triple.mytrip.domain.common;

public enum TripCategory {

    ACCOMMODATIONS("숙박", "숙소"),
    FLIGHT("항공", "항공"),
    TRAFFIC("교통", "교통"),
    TOUR("관광", "관광명소"),
    FOOD("식비", "음식점"),
    SHOPPING("쇼핑", "쇼핑"),
    ETC("기타", "기타");

    private final String budgetCategory;
    private final String placeCategory;

    TripCategory(String budgetCategory, String placeCategory) {
        this.budgetCategory = budgetCategory;
        this.placeCategory = placeCategory;
    }

    public String getBudgetCategory() {
        return budgetCategory;
    }

    public String getPlaceCategory() {
        return placeCategory;
    }
}
