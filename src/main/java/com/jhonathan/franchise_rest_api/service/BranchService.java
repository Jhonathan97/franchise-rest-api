package com.jhonathan.franchise_rest_api.service;

import com.jhonathan.franchise_rest_api.domain.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BranchService {

    Branch add(Long franchiseId, String name);

    Branch rename (Long franchiseId, Long branchId, String newName);

    Page<Branch> listByFranchise(Long franchiseId, Pageable pageable);
}
