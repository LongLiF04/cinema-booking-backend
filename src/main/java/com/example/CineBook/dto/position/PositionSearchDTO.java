package com.example.CineBook.dto.position;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionSearchDTO {
    private String code;
    private String name;
    private String searchTerm;
    private int page = 0;
    private int size = 10;
}
