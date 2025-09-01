package com.jhonathan.franchise_rest_api.service;

import com.jhonathan.franchise_rest_api.domain.Branch;
import com.jhonathan.franchise_rest_api.domain.Product;
import com.jhonathan.franchise_rest_api.repository.BranchRepository;
import com.jhonathan.franchise_rest_api.repository.ProductRepository;
import com.jhonathan.franchise_rest_api.repository.ProductTopRow;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;

    public ProductServiceImpl(ProductRepository productRepository, BranchRepository branchRepository) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
    }

    @Transactional
    @Override
    public Product add(Long franchiseId, Long branchId, String name, Integer stock) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new EntityNotFoundException("Branch %d not found".formatted(branchId)));

        if (!branch.getFranchise().getId().equals(franchiseId))
            throw new IllegalArgumentException("Branch not in franchise");

        String normalized = name == null ? null : name.trim();

        if (normalized == null || normalized.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }

        if (productRepository.existsByBranchIdAndNameIgnoreCase(branchId, normalized))
            throw new IllegalArgumentException("Product already exists in branch");

        Product p = new Product();
        p.setName(normalized);
        p.setBranch(branch);
        p.setStock(stock);

        try {
            return productRepository.save(p);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Product already exists in branch", e);
        }
    }

    @Override
    public Product delete(Long franchiseId, Long branchId, Long productId) {
        Product p = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product %d not found".formatted(productId)));

        if (!p.getBranch().getId().equals(branchId) || !p.getBranch().getFranchise().getId().equals(franchiseId))
            throw new IllegalArgumentException("Product not in given branch/franchise");

        productRepository.delete(p);
        return p;
    }

    @Transactional
    @Override
    public Product updateStock(Long franchiseId, Long branchId, Long productId, Integer stock) {
        if (stock == null || stock < 0)
            throw new IllegalArgumentException("Stock must be >= 0");
        Product p = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product %d not found".formatted(productId)));

        if (!p.getBranch().getId().equals(branchId) || !p.getBranch().getFranchise().getId().equals(franchiseId))
            throw new IllegalArgumentException("Product not in given branch/franchise");

        p.setStock(stock);
        return productRepository.save(p);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductTopRow> topProductsPerBranch(Long franchiseId) {
        return productRepository.findTopProductsPerBranch(franchiseId);
    }

    @Override
    public Product rename(Long franchiseId, Long branchId, Long productId, String newName) {
        Product p = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product %d not found".formatted(productId)));

        if (!p.getBranch().getId().equals(branchId) || !p.getBranch().getFranchise().getId().equals(franchiseId))
            throw new IllegalArgumentException("Product not in given branch/franchise");

        String normalized = newName == null ? null : newName.trim();
        if (normalized == null || normalized.isBlank())
            throw new IllegalArgumentException("Product name cannot be empty");

        p.setName(normalized);
        try {
            return productRepository.save(p);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Product name already exists", e);
        }
    }

    @Transactional(readOnly = true)
    public Page<Product> listByBranch(Long franchiseId, Long branchId, Pageable pageable) {
        Branch b = branchRepository.findById(branchId)
                .orElseThrow(() -> new EntityNotFoundException("Branch %d not found".formatted(branchId)));
        if (!b.getFranchise().getId().equals(franchiseId)) {
            throw new IllegalArgumentException("Branch not in franchise");
        }
        return productRepository.findByBranchId(branchId, pageable);
    }
}
