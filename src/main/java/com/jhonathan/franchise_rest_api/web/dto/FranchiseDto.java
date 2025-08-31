package com.jhonathan.franchise_rest_api.web.dto;

import com.jhonathan.franchise_rest_api.domain.Franchise;

public record FranchiseDto(Long id, String name) {
    public static FranchiseDto from(Franchise f) {
        return new FranchiseDto(f.getId(), f.getName());
    }
}
