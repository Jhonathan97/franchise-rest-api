package com.jhonathan.franchise_rest_api.web.controllers;

import com.jhonathan.franchise_rest_api.domain.Branch;
import com.jhonathan.franchise_rest_api.service.BranchService;
import com.jhonathan.franchise_rest_api.web.dto.BranchDto;
import com.jhonathan.franchise_rest_api.web.dto.CreateBranchRequest;
import com.jhonathan.franchise_rest_api.web.dto.ProductTopStockResponse;
import com.jhonathan.franchise_rest_api.web.dto.UpdateNameRequest;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/franchises/{franchiseId}/branches")
@Validated
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping
    public ResponseEntity<BranchDto> add(@PathVariable Long franchiseId, @Valid @RequestBody CreateBranchRequest req) {
        Branch b = branchService.add(franchiseId, req.name());
        return ResponseEntity.status(HttpStatus.CREATED).body(BranchDto.from(b));
    }

    @PatchMapping("/{branchId}")
    public BranchDto rename(@PathVariable Long franchiseId, @PathVariable Long branchId, @Valid @RequestBody UpdateNameRequest req) {
        return BranchDto.from(branchService.rename(franchiseId, branchId, req.name()));
    }

    @GetMapping
    public Page<BranchDto> list(@PathVariable Long franchiseId, @ParameterObject
            @PageableDefault(size = 20, sort = "name") Pageable pageable
    ) {
        return branchService.listByFranchise(franchiseId, pageable).map(BranchDto::from);
    }
}
