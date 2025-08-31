package com.jhonathan.franchise_rest_api.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateBranchRequest(@NotBlank @Size(max = 120) String name) {
}
