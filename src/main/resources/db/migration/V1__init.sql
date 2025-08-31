CREATE TABLE franchises (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(120) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE branches (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    franchise_id BIGINT NOT NULL,
    name VARCHAR(120) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_branch_franchise FOREIGN KEY (franchise_id) REFERENCES franchises(id),
    CONSTRAINT uq_branch_name UNIQUE (franchise_id, name)
);


CREATE TABLE products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    branch_id BIGINT NOT NULL,
    name VARCHAR(120) NOT NULL,
    stock INT NOT NULL CHECK (stock >= 0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_product_branch FOREIGN KEY (branch_id) REFERENCES branches(id),
    CONSTRAINT uq_product_name UNIQUE (branch_id, name)
);

CREATE INDEX idx_products_branch ON products(branch_id);