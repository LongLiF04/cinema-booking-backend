package com.example.CineBook.service;

import com.example.CineBook.common.dto.response.PageResponse;
import com.example.CineBook.dto.position.PositionRequest;
import com.example.CineBook.dto.position.PositionResponse;
import com.example.CineBook.dto.position.PositionSearchDTO;

import java.util.List;
import java.util.UUID;

public interface PositionService {
    List<PositionResponse> getAllPositions();
    PageResponse<PositionResponse> searchPositions(PositionSearchDTO searchDTO);
    PositionResponse getPositionById(UUID id);
    PositionResponse createPosition(PositionRequest request);
    PositionResponse updatePosition(UUID id, PositionRequest request);
    void deletePosition(UUID id);
}
