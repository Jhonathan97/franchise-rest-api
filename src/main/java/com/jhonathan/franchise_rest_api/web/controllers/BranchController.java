package com.jhonathan.franchise_rest_api.web.controllers;

import com.jhonathan.franchise_rest_api.domain.Branch;
import com.jhonathan.franchise_rest_api.service.BranchService;
import com.jhonathan.franchise_rest_api.web.dto.BranchDto;
import com.jhonathan.franchise_rest_api.web.dto.CreateBranchRequest;
import com.jhonathan.franchise_rest_api.web.dto.UpdateNameRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/franchises/{franchiseId}/branches")
@Validated
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping
    public ResponseEntity<BranchDto> add(@PathVariable Long franchiseId, @Valid @RequestBody CreateBranchRequest req){
        Branch b = branchService.add(franchiseId, req.name());
        return ResponseEntity.status(HttpStatus.CREATED).body(BranchDto.from(b));
    }

    @PatchMapping("/{branchId}")
    public BranchDto rename(@PathVariable Long franchiseId, @PathVariable Long branchId, @Valid @RequestBody UpdateNameRequest req){
        return BranchDto.from(branchService.rename(franchiseId, branchId, req.name()));
    }
}
