package com.example.CineBook.dto.customer;

import com.example.CineBook.common.dto.request.SearchBaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerSearchDTO extends SearchBaseDto {
    private String keyword; // Search by name, email, phone
    private String membershipLevel;
    private String city;
}
