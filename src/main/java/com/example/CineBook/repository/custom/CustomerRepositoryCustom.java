package com.example.CineBook.repository.custom;

import com.example.CineBook.dto.customer.CustomerSearchDTO;
import com.example.CineBook.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

public interface CustomerRepositoryCustom {
    Page<Customer> searchWithFilters(CustomerSearchDTO searchDTO, Pageable pageable);
}
