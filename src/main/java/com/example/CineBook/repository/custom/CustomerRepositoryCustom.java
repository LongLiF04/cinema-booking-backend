package com.example.CineBook.repository.custom;

import com.example.CineBook.dto.customer.CustomerSearchDTO;
import com.example.CineBook.model.Customer;
import org.springframework.data.domain.Page;

public interface CustomerRepositoryCustom {
    Page<Customer> findAllWithFilters(CustomerSearchDTO searchDTO);
}
