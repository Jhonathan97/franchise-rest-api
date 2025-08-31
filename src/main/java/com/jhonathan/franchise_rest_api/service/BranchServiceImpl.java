package com.jhonathan.franchise_rest_api.service;

import com.jhonathan.franchise_rest_api.domain.Branch;
import com.jhonathan.franchise_rest_api.domain.Franchise;
import com.jhonathan.franchise_rest_api.repository.BranchRepository;
import com.jhonathan.franchise_rest_api.repository.FranchiseRepository;

import java.util.Optional;

public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final FranchiseRepository franchiseRepository;

    public BranchServiceImpl(BranchRepository branchRepository, FranchiseRepository franchiseRepository) {
        this.branchRepository = branchRepository;
        this.franchiseRepository = franchiseRepository;
    }

    @Override
    public Branch add(Long franchiseId, String name) {
        Franchise franchise = franchiseRepository.findById(franchiseId).orElseThrow();
        if (branchRepository.existsByFranchiseIdAndNameIgnoreCase(franchiseId, name))
            throw new IllegalArgumentException("Branch name already exists in franchise");
        Branch newBranch = new Branch();
        newBranch.setFranchise(franchise);
        newBranch.setName(name);
        return newBranch;
    }

    @Override
    public Optional<Branch> rename(Long franchiseId, Long branchId, String newName) {
        return Optional.empty();
    }
}
