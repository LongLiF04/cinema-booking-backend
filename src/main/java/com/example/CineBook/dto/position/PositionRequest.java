package com.example.CineBook.dto.position;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionRequest {
    @NotBlank(message = "Position code is required")
    private String code;
    
    @NotBlank(message = "Position name is required")
    private String name;
    
    private String description;
}
