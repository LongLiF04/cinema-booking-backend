package com.example.CineBook.controller;

import com.example.CineBook.common.dto.response.PageResponse;
import com.example.CineBook.common.response.ApiResponse;
import com.example.CineBook.dto.city.CityResponse;
import com.example.CineBook.dto.city.CitySearchDTO;
import com.example.CineBook.dto.city.CreateCityRequest;
import com.example.CineBook.dto.city.UpdateCityRequest;
import com.example.CineBook.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "City Management", description = "APIs quản lý thành phố")
@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @PostMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @Operation(summary = "Tạo thành phố mới")
    public ResponseEntity<ApiResponse<CityResponse>> createCity(@Valid @RequestBody CreateCityRequest request) {
        return ResponseEntity.ok(ApiResponse.success(cityService.createCity(request)));
    }

    @GetMapping
    @Operation(summary = "Lấy tất cả thành phố")
    public ResponseEntity<ApiResponse<List<CityResponse>>> getAllCities() {
        return ResponseEntity.ok(ApiResponse.success(cityService.getAllCities()));
    }

    @GetMapping("/search")
    @Operation(summary = "Tìm kiếm thành phố")
    public ResponseEntity<ApiResponse<PageResponse<CityResponse>>> searchCities(@ModelAttribute CitySearchDTO searchDTO) {
        return ResponseEntity.ok(ApiResponse.success(cityService.searchCities(searchDTO)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Xem chi tiết thành phố")
    public ResponseEntity<ApiResponse<CityResponse>> getCityById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(cityService.getCityById(id)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @Operation(summary = "Cập nhật thành phố")
    public ResponseEntity<ApiResponse<CityResponse>> updateCity(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateCityRequest request) {
        return ResponseEntity.ok(ApiResponse.success(cityService.updateCity(id, request)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @Operation(summary = "Xóa thành phố")
    public ResponseEntity<ApiResponse<Void>> deleteCity(@PathVariable UUID id) {
        cityService.deleteCity(id);
        return ResponseEntity.ok(ApiResponse.success());
    }
}
