package com.jhonathan.franchise_rest_api.web.controllers;


import com.jhonathan.franchise_rest_api.domain.Product;
import com.jhonathan.franchise_rest_api.service.ProductService;
import com.jhonathan.franchise_rest_api.web.dto.*;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/franchises/{franchiseId}/branches/{branchId}/products")
@Validated
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDto> add(@PathVariable Long franchiseId, @PathVariable Long branchId,
                                          @Valid @RequestBody CreateProductRequest req) {
        Product p = productService.add(franchiseId, branchId, req.name(), req.stock());
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductDto.from(p));
    }

    @PatchMapping("/{productId}/stock")
    public ProductDto updateStock(@PathVariable Long franchiseId, @PathVariable Long branchId,
                                  @PathVariable Long productId, @Valid @RequestBody UpdateStockRequest req) {
        return ProductDto.from(productService.updateStock(franchiseId, branchId, productId, req.stock()));
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long franchiseId, @PathVariable Long branchId, @PathVariable Long productId) {
        productService.delete(franchiseId, branchId, productId);
    }

    @PatchMapping("/{productId}")
    public ProductDto rename(@PathVariable Long franchiseId, @PathVariable Long branchId, @PathVariable Long productId, @Valid @RequestBody UpdateNameRequest req) {
        return ProductDto.from(productService.rename(franchiseId, branchId, productId, req.name()));
    }

    @GetMapping
    public Page<ProductDto> list(
            @PathVariable Long franchiseId,
            @PathVariable Long branchId,
            @ParameterObject
            @PageableDefault(size = 20)
            Pageable pageable
    ) {
        return productService.listByBranch(franchiseId, branchId, pageable)
                .map(ProductDto::from);
    }
}
