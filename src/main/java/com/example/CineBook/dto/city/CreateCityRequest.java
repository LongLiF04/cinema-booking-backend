package com.example.CineBook.dto.city;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCityRequest {
    @NotBlank(message = "Tên thành phố không được để trống")
    private String name;
    
    private String code;
}
