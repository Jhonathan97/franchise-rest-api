package com.jhonathan.franchise_rest_api.repository;

import com.jhonathan.franchise_rest_api.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByBranchIdAndNameIgnoreCase(Long branchId, String name);
    Optional<Product> findTopByBranchIdOrderByStockDesc(Long branchId);
}
