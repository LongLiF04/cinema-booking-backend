package com.example.CineBook.service;

import com.example.CineBook.common.dto.response.PageResponse;
import com.example.CineBook.dto.customer.CustomerCreateRequest;
import com.example.CineBook.dto.customer.CustomerResponse;
import com.example.CineBook.dto.customer.CustomerSearchDTO;
import com.example.CineBook.dto.customer.CustomerUpdateRequest;

import java.util.UUID;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerCreateRequest request);
    CustomerResponse getCustomerById(UUID userId);
    CustomerResponse updateCustomer(UUID userId, CustomerUpdateRequest request);
    void deleteCustomer(UUID userId);
    PageResponse<CustomerResponse> searchCustomers(CustomerSearchDTO searchDTO);
    CustomerResponse getCurrentCustomer();
    CustomerResponse updateCurrentCustomer(CustomerUpdateRequest request);
}
