package com.example.CineBook.common.constant;

import lombok.Getter;

@Getter
public enum MembershipLevel {
    STANDARD("STANDARD", "Cấp độ khởi điểm với các quyền lợi tiêu chuẩn."),
    VIP("VIP", "Cấp độ nâng cao với ưu đãi đặc biệt và tích điểm nhanh hơn."),
    VVIP("VVIP", "Cấp độ cao nhất với quyền lợi ưu tiên và ưu đãi lớn nhất.");

    private final String value;
    private final String description;

    MembershipLevel(String value, String description) {
        this.value = value;
        this.description = description;
    }
}
