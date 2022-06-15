package com.triple.mytrip.domain.trip;

public enum TripStyle {
    ACTIVITY("체험·엑티비티"),
    HOTPLACE("SNS 핫플레이스"),
    NATURAL("자연과 함께"),
    FAMOUS("유명 관광지는 필수"),
    HEALING("여유롭게 힐링"),
    CULTURE("문화·예술·역사"),
    TOURIST("여행지 느낌 물신"),
    SHOPPING("쇼핑은 열정적으로"),
    FOOD("관광보다 먹방");

    private final String korName;

    TripStyle(String korName) {
        this.korName = korName;
    }

    public String getKorName() {
        return korName;
    }

}
