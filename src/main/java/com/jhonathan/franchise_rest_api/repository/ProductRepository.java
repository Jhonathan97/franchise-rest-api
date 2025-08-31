package com.jhonathan.franchise_rest_api.repository;

import com.jhonathan.franchise_rest_api.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByBranchIdAndNameIgnoreCase(Long branchId, String name);

    @Query(value = """
        SELECT branch_id   AS branchId,
               branchName  AS branchName,
               product_id  AS productId,
               productName AS productName,
               stock       AS stock
        FROM (
            SELECT b.id   AS branch_id,
                   b.name AS branchName,
                   p.id   AS product_id,
                   p.name AS productName,
                   p.stock AS stock,
                   ROW_NUMBER() OVER (
                       PARTITION BY b.id
                       ORDER BY p.stock DESC, p.id ASC
                   ) AS rn
            FROM branches b
            JOIN products p ON p.branch_id = b.id
            WHERE b.franchise_id = :franchiseId
        ) t
        WHERE rn = 1
        ORDER BY branchName
        """, nativeQuery = true)
    List<ProductTopRow> findTopProductsPerBranch(@Param("franchiseId") Long franchiseId);
}
