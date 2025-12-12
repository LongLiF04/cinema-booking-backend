package com.example.CineBook.repository.custom;

import com.example.CineBook.dto.city.CitySearchDTO;
import com.example.CineBook.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityRepositoryCustom {
    Page<City> searchWithFilters(CitySearchDTO searchDTO, Pageable pageable);
}
