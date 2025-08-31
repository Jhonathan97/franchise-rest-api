package com.jhonathan.franchise_rest_api.web.dto;

import com.jhonathan.franchise_rest_api.domain.Product;

public record ProductDto(Long id, Long branchId, String name, Integer stock) {
    public static ProductDto from(Product p) {
        return new ProductDto(p.getId(), p.getBranch().getId(), p.getName(), p.getStock());
    }
}