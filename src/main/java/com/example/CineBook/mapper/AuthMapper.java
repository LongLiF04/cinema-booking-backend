package com.example.CineBook.mapper;

import com.example.CineBook.dto.auth.RegisterRequest;
import com.example.CineBook.dto.auth.RegisterResponse;

public interface AuthMapper extends BaseMapper<RegisterRequest, RegisterResponse> {
    RegisterResponse map(RegisterRequest request);
}
