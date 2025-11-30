package com.example.CineBook.dto.customer;

import com.example.CineBook.common.constant.GenderEnum;
import com.example.CineBook.common.constant.MembershipLevel;
import com.example.CineBook.common.constant.MembershipLevelEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class CustomerResponse {
    private UUID userId;
    private String username;
    private String name;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private GenderEnum gender;
    private String address;
    private String city;
    private MembershipLevelEnum membershipLevel;
    private Integer loyaltyPoints;
}
