package com.example.CineBook.mapper;

import com.example.CineBook.dto.customer.CustomerCreateRequest;
import com.example.CineBook.dto.customer.CustomerResponse;
import com.example.CineBook.model.Customer;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.Map;
import java.util.UUID;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
    
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "membershipLevel", constant = "BRONZE")
    @Mapping(target = "loyaltyPoints", constant = "0")
    Customer toEntity(CustomerCreateRequest request, UUID userId);

    CustomerResponse toResponse(Customer customer);
    
    CustomerResponse toResponse(Customer customer, @Context Map<Object, Object> context);

    default Page<CustomerResponse> mapPage(Page<Customer> entityPage, Map<Object, Object> context) {
        return entityPage.map(entity -> toResponse(entity, context));
    }
}
