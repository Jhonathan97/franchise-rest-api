package com.jhonathan.franchise_rest_api.service;

import com.jhonathan.franchise_rest_api.domain.Franchise;
import com.jhonathan.franchise_rest_api.repository.FranchiseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FranchiseServiceImpl implements FranchiseService {

    private final FranchiseRepository repository;

    public FranchiseServiceImpl(FranchiseRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public Franchise create(String name) {
        String normalized = name == null ? null : name.trim();
        if (normalized == null || normalized.isBlank())
            throw new IllegalArgumentException("Franchise name cannot be empty");

        if (repository.existsByNameIgnoreCase(normalized))
            throw new IllegalArgumentException("Franchise name already exists");

        Franchise f = new Franchise();
        f.setName(normalized);
        try {
            return repository.save(f);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Franchise name already exists", e);
        }
    }

    @Transactional
    @Override
    public Franchise rename(Long id, String newName) {
        Franchise f = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Franchise %d not found".formatted(id)));
        String normalized = newName == null ? null : newName.trim();

        if (normalized == null || normalized.isBlank())
            throw new IllegalArgumentException("Franchise name cannot be empty");

        if (repository.existsByNameIgnoreCase(normalized)
                && !f.getName().equalsIgnoreCase(normalized))
            throw new IllegalArgumentException("Franchise name already exists");
        f.setName(normalized);
        try {
            return repository.save(f);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Franchise name already exists", e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Franchise> findByNameContainingIgnoreCase(String filter, Pageable pageable) {
        if (filter == null || filter.isBlank()) {
            return repository.findAll(pageable);
        }
        return repository.findByNameContainingIgnoreCase(filter.trim(), pageable);
    }
}
