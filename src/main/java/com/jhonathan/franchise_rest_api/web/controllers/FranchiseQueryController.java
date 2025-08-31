package com.jhonathan.franchise_rest_api.web.controllers;

import com.jhonathan.franchise_rest_api.service.ProductService;
import com.jhonathan.franchise_rest_api.web.dto.ProductTopStockResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/franchises")
@Validated
public class FranchiseQueryController {
    private final ProductService productService;

    public FranchiseQueryController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{franchiseId}/top-products")
    public List<ProductTopStockResponse> topProductsPerBranch(@PathVariable Long franchiseId){
        return productService.topProductsPerBranch(franchiseId)
                .stream().map(ProductTopStockResponse::from).toList();
    }
}
