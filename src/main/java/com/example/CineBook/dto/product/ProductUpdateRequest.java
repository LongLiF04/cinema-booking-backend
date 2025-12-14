package com.example.CineBook.dto.product;

import com.example.CineBook.common.constant.ProductType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private ProductType type;
    private Boolean isActive;
}
