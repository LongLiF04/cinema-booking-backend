package com.example.CineBook.common.constant;

import lombok.Getter;

@Getter
public enum GenderEnum {
    MALE("MALE", "Nam"),
    FEMALE("FEMALE", "Nữ"),
    OTHER("OTHER", "Khác");

    private final String value;
    private final String description;

    GenderEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }
}
