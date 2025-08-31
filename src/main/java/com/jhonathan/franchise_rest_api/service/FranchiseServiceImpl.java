package com.jhonathan.franchise_rest_api.service;

import com.jhonathan.franchise_rest_api.domain.Franchise;
import com.jhonathan.franchise_rest_api.repository.FranchiseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FranchiseServiceImpl implements FranchiseService {

    private final FranchiseRepository repository;

    public FranchiseServiceImpl(FranchiseRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public Franchise create(String name) {
        if (repository.existsByNameIgnoreCase(name))
            throw new IllegalArgumentException("Franchise name already exists");
        Franchise franchise = new Franchise();
        franchise.setName(name);
        return repository.save(franchise);
    }

    @Transactional
    @Override
    public Optional<Franchise> rename(Long id, String newName) {
        Optional<Franchise> franchiseOptional = repository.findById(id);
        if (franchiseOptional.isPresent()){
            Franchise franchise = franchiseOptional.orElseThrow();
            if(repository.existsByNameIgnoreCase(newName)) throw new IllegalArgumentException("Franchise name already exists");
            franchise.setName(newName);
        }
        return franchiseOptional;
    }
}
