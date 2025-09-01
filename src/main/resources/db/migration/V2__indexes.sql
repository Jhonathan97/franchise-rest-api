CREATE INDEX idx_branches_franchise_name ON branches(franchise_id, name);
CREATE INDEX idx_products_branch_stock ON products(branch_id, stock);