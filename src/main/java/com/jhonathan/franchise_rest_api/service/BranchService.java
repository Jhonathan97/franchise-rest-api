package com.jhonathan.franchise_rest_api.service;

import com.jhonathan.franchise_rest_api.domain.Branch;

import java.util.Optional;

public interface BranchService {

    Branch add(Long franchiseId, String name);

    Optional<Branch> rename (Long franchiseId, Long branchId, String newName);
}
