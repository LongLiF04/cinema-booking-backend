package com.example.CineBook.mapper;

import com.example.CineBook.dto.city.CityResponse;
import com.example.CineBook.dto.city.CreateCityRequest;
import com.example.CineBook.dto.city.UpdateCityRequest;
import com.example.CineBook.model.City;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CityMapper {
    
    City toEntity(CreateCityRequest request);
    
    void updateEntityFromDto(UpdateCityRequest request, @MappingTarget City city);
    
    CityResponse toResponse(City city);
}
