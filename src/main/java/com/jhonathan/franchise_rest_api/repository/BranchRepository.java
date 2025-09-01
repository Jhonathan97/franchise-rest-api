package com.jhonathan.franchise_rest_api.repository;

import com.jhonathan.franchise_rest_api.domain.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    Page<Branch> findByFranchiseId(Long franchiseId, Pageable pageable);
    boolean existsByFranchiseIdAndNameIgnoreCase(Long franchiseId, String name);
}
