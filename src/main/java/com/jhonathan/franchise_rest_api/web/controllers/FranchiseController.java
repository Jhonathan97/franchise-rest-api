package com.jhonathan.franchise_rest_api.web.controllers;

import com.jhonathan.franchise_rest_api.domain.Branch;
import com.jhonathan.franchise_rest_api.domain.Franchise;
import com.jhonathan.franchise_rest_api.domain.Product;
import com.jhonathan.franchise_rest_api.service.BranchService;
import com.jhonathan.franchise_rest_api.service.FranchiseService;
import com.jhonathan.franchise_rest_api.service.ProductService;
import com.jhonathan.franchise_rest_api.web.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/franchises")
@Validated
public class FranchiseController {
    private final FranchiseService franchiseService;

    public FranchiseController(FranchiseService franchiseService, BranchService branchService, ProductService productService) {
        this.franchiseService = franchiseService;
    }

    @PostMapping
    public ResponseEntity<FranchiseDto> create(@Valid @RequestBody CreateFranchiseRequest req){
        Franchise f = franchiseService.create(req.name());
        return ResponseEntity.status(HttpStatus.CREATED).body(FranchiseDto.from(f));
    }

    @PatchMapping("/{id}")
    public FranchiseDto rename(@PathVariable Long id, @Valid @RequestBody UpdateNameRequest req){
        return FranchiseDto.from(franchiseService.rename(id, req.name()));
    }


}
