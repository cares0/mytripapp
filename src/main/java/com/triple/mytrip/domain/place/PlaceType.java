package com.triple.mytrip.domain.place;

public enum PlaceType {
    TOUR("관광 명소"),
    ACCOMMODATION("숙소"),
    RESTAURANT("음식점"),
    SHOP("쇼핑");

    private final String korName;

    PlaceType(String korName) {
        this.korName = korName;
    }

    public String getKorName() {
        return korName;
    }
}
