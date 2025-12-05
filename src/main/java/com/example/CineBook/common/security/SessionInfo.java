package com.example.CineBook.common.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public record SessionInfo(
        @JsonProperty("userId") UUID userId,
        @JsonProperty("username") String username,
        @JsonProperty("refreshToken") String refreshToken,
        @JsonProperty("expiry") Date expiry,
        @JsonProperty("device") String device,
        @JsonProperty("ipAddress") String ipAddress
) implements Serializable {
}


///  <============== KIẾN THỨC HỌC ĐƯỢC ===============>

/**
 * public record SessionInfo
 * --> record tự động tạo:
 *     - Constructor
 *     - Getter
 *     - equals(), hashCode(), toString()
 *     và mặc định là immutable
 *
 *     @JsonProperty: Tránh trường hợp fe gửi tên khác với tên thuộc tính
 *     kiểu nó quyết định fe gửi gì
 */


