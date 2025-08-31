package com.jhonathan.franchise_rest_api.web.dto;

public record ProductTopStockResponse(Long branchId, String branchName, Long productId, String productName,
                                      Integer stock) {

}
