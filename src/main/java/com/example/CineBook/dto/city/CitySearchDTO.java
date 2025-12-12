package com.example.CineBook.dto.city;

import com.example.CineBook.common.dto.request.SearchBaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CitySearchDTO extends SearchBaseDto {
    private String keyword;
}
