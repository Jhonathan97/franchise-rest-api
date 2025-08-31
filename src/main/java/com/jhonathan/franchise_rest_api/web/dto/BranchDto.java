package com.jhonathan.franchise_rest_api.web.dto;

import com.jhonathan.franchise_rest_api.domain.Branch;

public record BranchDto(Long id, Long franchiseId, String name) {
    public static BranchDto from(Branch b) {
        return new BranchDto(b.getId(), b.getFranchise().getId(), b.getName());
    }
}