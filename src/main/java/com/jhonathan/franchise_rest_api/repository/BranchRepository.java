package com.jhonathan.franchise_rest_api.repository;

import com.jhonathan.franchise_rest_api.domain.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    List<Branch> findByFranchiseId(Long franchiseId);
    boolean existsByFranchiseIdAndNameIgnoreCase(Long franchiseId, String name);
}
