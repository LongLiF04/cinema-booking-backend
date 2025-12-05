package com.example.CineBook.common.constant;

import lombok.Getter;

@Getter
public enum AgeRating {
    P("P", "Phổ biến - Mọi lứa tuổi"),      // Phổ biến - Mọi lứa tuổi
    K("K", "Trẻ em dưới 13 tuổi có cha mẹ đi cùng"),      // Trẻ em dưới 13 tuổi có cha mẹ đi cùng
    T13("T13", "Từ 13 tuổi trở lên"),    // Từ 13 tuổi trở lên
    T16("T16", "Từ 16 tuổi trở lên"),    // Từ 16 tuổi trở lên
    T18("T18", "Từ 18 tuổi trở lên"),    // Từ 18 tuổi trở lên
    C("C", "Cấm chiếu");  // Cấm chiếu

    private final String value;
    private final String description;

    AgeRating(String value, String description) {
        this.value = value;
        this.description = description;
    }
}
