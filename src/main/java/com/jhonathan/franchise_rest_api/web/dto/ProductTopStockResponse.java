package com.jhonathan.franchise_rest_api.web.dto;

import com.jhonathan.franchise_rest_api.domain.Product;
import com.jhonathan.franchise_rest_api.repository.ProductTopRow;

public record ProductTopStockResponse(
        Long branchId, String branchName,
        Long productId, String productName,
        Integer stock
) {
    public static ProductTopStockResponse from(ProductTopRow r) {
        return new ProductTopStockResponse(
                r.getBranchId(), r.getBranchName(),
                r.getProductId(), r.getProductName(),
                r.getStock()
        );
    }

    public static ProductTopStockResponse from(Product p) {
        return new ProductTopStockResponse(
                p.getBranch().getId(), p.getBranch().getName(),
                p.getId(), p.getName(), p.getStock()
        );
    }
}
