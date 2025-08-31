package com.jhonathan.franchise_rest_api.service;

import com.jhonathan.franchise_rest_api.domain.Product;
import com.jhonathan.franchise_rest_api.repository.ProductTopRow;

import java.util.List;

public interface ProductService {

    Product add(Long franchiseId, Long branchId, String name, Integer stock);

    Product delete(Long franchiseId, Long branchId, Long productId);

    Product updateStock(Long franchiseId, Long branchId, Long productId, Integer stock);

    List<ProductTopRow> topProductsPerBranch(Long franchiseId);
}
