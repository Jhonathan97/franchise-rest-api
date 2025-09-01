package com.jhonathan.franchise_rest_api.service;

import com.jhonathan.franchise_rest_api.domain.Franchise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FranchiseService {

    Franchise create(String name);

    Franchise rename(Long id, String newName);

    Page<Franchise> findByNameContainingIgnoreCase(String filter, Pageable pageable);
}
