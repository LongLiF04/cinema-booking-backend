package com.example.CineBook.service.impl;

import com.example.CineBook.common.exception.BusinessException;
import com.example.CineBook.common.exception.MessageCode;
import com.example.CineBook.dto.product.ProductCreateRequest;
import com.example.CineBook.dto.product.ProductResponse;
import com.example.CineBook.dto.product.ProductUpdateRequest;
import com.example.CineBook.model.Product;
import com.example.CineBook.repository.irepository.ProductRepository;
import com.example.CineBook.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public ProductResponse createProduct(ProductCreateRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .imageUrl(request.getImageUrl())
                .type(request.getType())
                .isActive(request.getIsActive() != null ? request.getIsActive() : true)
                .build();

        Product saved = productRepository.save(product);
        return toResponse(saved);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(UUID id, ProductUpdateRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BusinessException(MessageCode.PRODUCT_NOT_FOUND));

        if (request.getName() != null) product.setName(request.getName());
        if (request.getDescription() != null) product.setDescription(request.getDescription());
        if (request.getPrice() != null) product.setPrice(request.getPrice());
        if (request.getImageUrl() != null) product.setImageUrl(request.getImageUrl());
        if (request.getType() != null) product.setType(request.getType());
        if (request.getIsActive() != null) product.setIsActive(request.getIsActive());

        Product updated = productRepository.save(product);
        return toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteProduct(UUID id) {
        if (!productRepository.existsById(id)) {
            throw new BusinessException(MessageCode.PRODUCT_NOT_FOUND);
        }
        productRepository.deleteById(id);
    }

    private ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .type(product.getType())
                .isActive(product.getIsActive())
                .build();
    }
}
