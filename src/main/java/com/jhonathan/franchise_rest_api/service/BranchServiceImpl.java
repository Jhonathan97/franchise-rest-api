package com.jhonathan.franchise_rest_api.service;

import com.jhonathan.franchise_rest_api.domain.Branch;
import com.jhonathan.franchise_rest_api.domain.Franchise;
import com.jhonathan.franchise_rest_api.repository.BranchRepository;
import com.jhonathan.franchise_rest_api.repository.FranchiseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final FranchiseRepository franchiseRepository;

    public BranchServiceImpl(BranchRepository branchRepository, FranchiseRepository franchiseRepository) {
        this.branchRepository = branchRepository;
        this.franchiseRepository = franchiseRepository;
    }

    @Transactional
    @Override
    public Branch add(Long franchiseId, String name) {
        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Franchise %d not found".formatted(franchiseId)));
        String normalized = name == null ? null : name.trim();
        if (branchRepository.existsByFranchiseIdAndNameIgnoreCase(franchiseId, normalized))
            throw new IllegalArgumentException("Branch name already exists in this franchise");

        Branch b = new Branch();
        b.setFranchise(franchise);
        b.setName(normalized);
        try {
            return branchRepository.save(b);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Branch name already exists in this franchise", e);
        }
    }

    @Transactional
    @Override
    public Branch rename(Long franchiseId, Long branchId, String newName) {
        Branch b = branchRepository.findById(branchId)
                .orElseThrow(() -> new EntityNotFoundException("Branch %d not found".formatted(branchId)));

        if (!b.getFranchise().getId().equals(franchiseId))
            throw new IllegalArgumentException("Branch not in franchise");

        String normalized = newName == null ? null : newName.trim();

        if (normalized == null || normalized.isBlank())
            throw new IllegalArgumentException("Branch name cannot be empty");

        boolean nameTaken = branchRepository.existsByFranchiseIdAndNameIgnoreCase(franchiseId, normalized);
        boolean changingToSame = b.getName().equalsIgnoreCase(normalized);

        if (nameTaken && !changingToSame)
            throw new IllegalArgumentException("Branch name already exists in this franchise");

        b.setName(normalized);
        try {
            return branchRepository.save(b);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Branch name already exists in this franchise", e);
        }
    }
}
