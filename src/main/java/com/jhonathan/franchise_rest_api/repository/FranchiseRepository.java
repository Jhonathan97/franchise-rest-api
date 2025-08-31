package com.jhonathan.franchise_rest_api.repository;

import com.jhonathan.franchise_rest_api.domain.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranchiseRepository extends JpaRepository<Franchise, Long> {

    boolean existsByNameIgnoreCase(String name);
}
