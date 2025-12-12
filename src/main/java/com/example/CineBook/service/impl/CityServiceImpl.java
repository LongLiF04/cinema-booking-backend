package com.example.CineBook.service.impl;

import com.example.CineBook.common.dto.response.PageResponse;
import com.example.CineBook.common.exception.BusinessException;
import com.example.CineBook.common.exception.MessageCode;
import com.example.CineBook.dto.city.CityResponse;
import com.example.CineBook.dto.city.CitySearchDTO;
import com.example.CineBook.dto.city.CreateCityRequest;
import com.example.CineBook.dto.city.UpdateCityRequest;
import com.example.CineBook.mapper.CityMapper;
import com.example.CineBook.model.City;
import com.example.CineBook.repository.irepository.CityRepository;
import com.example.CineBook.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    @Transactional
    public CityResponse createCity(CreateCityRequest request) {
        if (cityRepository.existsByName(request.getName())) {
            throw new BusinessException(MessageCode.CITY_NAME_ALREADY_EXISTS);
        }

        City city = cityMapper.toEntity(request);
        City saved = cityRepository.save(city);
        return cityMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CityResponse> getAllCities() {
        return cityRepository.findAll().stream()
                .filter(city -> !Boolean.TRUE.equals(city.getIsDelete()))
                .map(cityMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<CityResponse> searchCities(CitySearchDTO searchDTO) {
        Pageable pageable = PageRequest.of(searchDTO.getPage() - 1, searchDTO.getSize());
        Page<City> entityPage = cityRepository.searchWithFilters(searchDTO, pageable);
        Page<CityResponse> responsePage = entityPage.map(cityMapper::toResponse);
        return PageResponse.of(responsePage);
    }

    @Override
    @Transactional(readOnly = true)
    public CityResponse getCityById(UUID id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new BusinessException(MessageCode.CITY_NOT_FOUND));
        
        if (Boolean.TRUE.equals(city.getIsDelete())) {
            throw new BusinessException(MessageCode.CITY_NOT_FOUND);
        }
        
        return cityMapper.toResponse(city);
    }

    @Override
    @Transactional
    public CityResponse updateCity(UUID id, UpdateCityRequest request) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new BusinessException(MessageCode.CITY_NOT_FOUND));

        if (request.getName() != null && !request.getName().equals(city.getName())) {
            if (cityRepository.existsByName(request.getName())) {
                throw new BusinessException(MessageCode.CITY_NAME_ALREADY_EXISTS);
            }
        }

        cityMapper.updateEntityFromDto(request, city);
        City updated = cityRepository.save(city);
        return cityMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteCity(UUID id) {
        if (!cityRepository.existsById(id)) {
            throw new BusinessException(MessageCode.CITY_NOT_FOUND);
        }

        cityRepository.softDeleteById(id);
    }
}
