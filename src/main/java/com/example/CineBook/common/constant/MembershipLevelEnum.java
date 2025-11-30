package com.example.CineBook.common.constant;

import lombok.Getter;

@Getter
public enum MembershipLevelEnum {
    BRONZE("BRONZE", "Đồng"),
    SILVER("SILVER", "Bạc"),
    GOLD("GOLD", "Vàng"),
    PLATINUM("PLATINUM", "Bạch kim");

    private final String value;
    private final String description;

    MembershipLevelEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public static MembershipLevelEnum fromValue(String value) {
        for (MembershipLevelEnum level : MembershipLevelEnum.values()) {
            if (level.value.equals(value)) {
                return level;
            }
        }
        throw new IllegalArgumentException("Invalid membership level: " + value);
    }
}
