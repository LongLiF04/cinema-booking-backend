package com.example.CineBook.controller;

import com.example.CineBook.common.dto.response.PageResponse;
import com.example.CineBook.common.response.ApiResponse;
import com.example.CineBook.dto.position.PositionRequest;
import com.example.CineBook.dto.position.PositionResponse;
import com.example.CineBook.dto.position.PositionSearchDTO;
import com.example.CineBook.service.PositionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Position Management", description = "APIs quản lý chức vụ nhân viên")
@RestController
@RequestMapping("/api/positions")
@RequiredArgsConstructor
public class PositionController {

    private final PositionService positionService;

    @GetMapping
    @Operation(summary = "Lấy danh sách tất cả chức vụ", description = "Dùng cho dropdown khi tạo/cập nhật employee")
    public ResponseEntity<ApiResponse<List<PositionResponse>>> getAllPositions() {
        return ResponseEntity.ok(ApiResponse.success(positionService.getAllPositions()));
    }

    @PostMapping("/search")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @Operation(summary = "Tìm kiếm và phân trang chức vụ")
    public ResponseEntity<ApiResponse<PageResponse<PositionResponse>>> searchPositions(
            @RequestBody PositionSearchDTO searchDTO) {
        return ResponseEntity.ok(ApiResponse.success(positionService.searchPositions(searchDTO)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @Operation(summary = "Lấy thông tin chức vụ theo ID")
    public ResponseEntity<ApiResponse<PositionResponse>> getPositionById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(positionService.getPositionById(id)));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @Operation(summary = "Tạo chức vụ mới")
    public ResponseEntity<ApiResponse<PositionResponse>> createPosition(
            @Valid @RequestBody PositionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(positionService.createPosition(request)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @Operation(summary = "Cập nhật chức vụ")
    public ResponseEntity<ApiResponse<PositionResponse>> updatePosition(
            @PathVariable UUID id,
            @Valid @RequestBody PositionRequest request) {
        return ResponseEntity.ok(ApiResponse.success(positionService.updatePosition(id, request)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @Operation(summary = "Xóa mềm chức vụ")
    public ResponseEntity<ApiResponse<Void>> deletePosition(@PathVariable UUID id) {
        positionService.deletePosition(id);
        return ResponseEntity.ok(ApiResponse.success());
    }
}
