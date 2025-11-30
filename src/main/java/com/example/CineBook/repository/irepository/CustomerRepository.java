package com.example.CineBook.repository.irepository;

import com.example.CineBook.model.Customer;
import com.example.CineBook.repository.custom.CustomerRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID>, CustomerRepositoryCustom {
    Optional<Customer> findByUserId(UUID userId);
}
