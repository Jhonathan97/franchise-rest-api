package com.jhonathan.franchise_rest_api.service;

import com.jhonathan.franchise_rest_api.domain.Franchise;

import java.util.Optional;

public interface FranchiseService {

    Franchise create(String name);

    Optional<Franchise> rename(Long id, String newName);
}
