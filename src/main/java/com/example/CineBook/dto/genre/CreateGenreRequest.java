package com.example.CineBook.dto.genre;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateGenreRequest {
    @NotBlank(message = "Tên thể loại không được để trống")
    private String name;
    
    private String description;
}
