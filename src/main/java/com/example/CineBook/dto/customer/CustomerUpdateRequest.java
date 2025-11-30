package com.example.CineBook.dto.customer;

import com.example.CineBook.common.constant.GenderEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerUpdateRequest {
    private String name;
    private String phone;
    private LocalDate dateOfBirth;
    private GenderEnum gender;
    private String address;
    private String city;
}
