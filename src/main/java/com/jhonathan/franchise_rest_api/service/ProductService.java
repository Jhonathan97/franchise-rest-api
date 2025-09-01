package com.jhonathan.franchise_rest_api.service;

import com.jhonathan.franchise_rest_api.domain.Franchise;
import com.jhonathan.franchise_rest_api.domain.Product;
import com.jhonathan.franchise_rest_api.repository.ProductTopRow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Product add(Long franchiseId, Long branchId, String name, Integer stock);

    Product delete(Long franchiseId, Long branchId, Long productId);

    Product updateStock(Long franchiseId, Long branchId, Long productId, Integer stock);

    List<ProductTopRow> topProductsPerBranch(Long franchiseId);

    Product rename(Long franchiseId, Long branchId, Long productId, String newName);

    Page<Product> listByBranch(Long franchiseId, Long branchId, Pageable pageable);

}
