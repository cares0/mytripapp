package com.triple.mytrip.domain.budget;

public enum PaymentPlan {
    CASH("현금"),
    CARD("카드");

    private final String korName;

    PaymentPlan(String korName) {
        this.korName = korName;
    }

    public String getKorName() {
        return korName;
    }
}
