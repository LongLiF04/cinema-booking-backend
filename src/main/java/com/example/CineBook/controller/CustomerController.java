package com.example.CineBook.controller;

import com.example.CineBook.common.dto.response.PageResponse;
import com.example.CineBook.common.response.ApiResponse;
import com.example.CineBook.dto.customer.CustomerCreateRequest;
import com.example.CineBook.dto.customer.CustomerResponse;
import com.example.CineBook.dto.customer.CustomerSearchDTO;
import com.example.CineBook.dto.customer.CustomerUpdateRequest;
import com.example.CineBook.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Customer Management", description = "APIs quản lý khách hàng")
@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @Operation(summary = "Tạo khách hàng mới", description = "ADMIN tạo Customer, tự động tạo User với role CUSTOMER")
    public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(
            @Valid @RequestBody CustomerCreateRequest request) {
        return ResponseEntity.ok(ApiResponse.success(customerService.createCustomer(request)));
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'STAFF')")
    @Operation(summary = "Xem thông tin khách hàng theo ID")
    public ResponseEntity<ApiResponse<CustomerResponse>> getCustomerById(@PathVariable UUID userId) {
        return ResponseEntity.ok(ApiResponse.success(customerService.getCustomerById(userId)));
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @Operation(summary = "Cập nhật thông tin khách hàng")
    public ResponseEntity<ApiResponse<CustomerResponse>> updateCustomer(
            @PathVariable UUID userId,
            @Valid @RequestBody CustomerUpdateRequest request) {
        return ResponseEntity.ok(ApiResponse.success(customerService.updateCustomer(userId, request)));
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @Operation(summary = "Xóa mềm khách hàng")
    public ResponseEntity<ApiResponse<Void>> deleteCustomer(@PathVariable UUID userId) {
        customerService.deleteCustomer(userId);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'STAFF')")
    @Operation(summary = "Tìm kiếm khách hàng", description = "Tìm theo tên, email, phone, membership level, city")
    public ResponseEntity<ApiResponse<PageResponse<CustomerResponse>>> searchCustomers(
            @ModelAttribute CustomerSearchDTO searchDTO) {
        return ResponseEntity.ok(ApiResponse.success(customerService.searchCustomers(searchDTO)));
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('CUSTOMER')")
    @Operation(summary = "Xem thông tin cá nhân", description = "Customer xem thông tin của chính mình")
    public ResponseEntity<ApiResponse<CustomerResponse>> getCurrentCustomer() {
        return ResponseEntity.ok(ApiResponse.success(customerService.getCurrentCustomer()));
    }

    @PutMapping("/me")
    @PreAuthorize("hasRole('CUSTOMER')")
    @Operation(summary = "Cập nhật thông tin cá nhân", description = "Customer cập nhật thông tin của chính mình")
    public ResponseEntity<ApiResponse<CustomerResponse>> updateCurrentCustomer(
            @Valid @RequestBody CustomerUpdateRequest request) {
        return ResponseEntity.ok(ApiResponse.success(customerService.updateCurrentCustomer(request)));
    }
}
