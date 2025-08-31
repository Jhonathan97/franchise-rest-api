package com.jhonathan.franchise_rest_api.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateStockRequest(@NotNull @Min(0) Integer stock) {
}
