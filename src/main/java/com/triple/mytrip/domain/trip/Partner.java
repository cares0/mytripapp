package com.triple.mytrip.domain.trip;

public enum Partner {

    SINGLE("혼자"),
    FRIEND("친구와"),
    COUPLE("연인과"),
    SPOUSE("배우자와"),
    CHILD("아이와"),
    PARENT("부모님과"),
    ETC("기타");

    private final String korName;

    Partner(String korName) {
        this.korName = korName;
    }

    public String getKorName() {
        return korName;
    }
}
