package com.triple.mytrip.domain.budget;

public enum PaymentPlan {
    CASH("ํ๊ธ"),
    CARD("์นด๋");

    private final String korName;

    PaymentPlan(String korName) {
        this.korName = korName;
    }

    public String getKorName() {
        return korName;
    }
}
