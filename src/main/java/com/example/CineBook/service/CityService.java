package com.example.CineBook.service;

import com.example.CineBook.common.dto.response.PageResponse;
import com.example.CineBook.dto.city.CityResponse;
import com.example.CineBook.dto.city.CitySearchDTO;
import com.example.CineBook.dto.city.CreateCityRequest;
import com.example.CineBook.dto.city.UpdateCityRequest;

import java.util.List;
import java.util.UUID;

public interface CityService {
    CityResponse createCity(CreateCityRequest request);
    List<CityResponse> getAllCities();
    PageResponse<CityResponse> searchCities(CitySearchDTO searchDTO);
    CityResponse getCityById(UUID id);
    CityResponse updateCity(UUID id, UpdateCityRequest request);
    void deleteCity(UUID id);
}
