CREATE TABLE vendors (
    id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone_number VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY NAME_UNIQUE (name)
) engine=InnoDB;

CREATE TABLE products (
    id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    price DECIMAL(10,2) NOT NULL,
    vendor_id INTEGER NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_PRODUCT_VENDOR FOREIGN KEY (vendor_id) REFERENCES vendors (id)
) engine=InnoDB;

CREATE TABLE order_lists (
    id INTEGER NOT NULL AUTO_INCREMENT,
    total_price DECIMAL(10,2) DEFAULT 0,
    vendor_id INTEGER,
    created_at DATETIME(6) NOT NULL,
    name VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT FK_ORDER_LIST_VENDOR FOREIGN KEY (vendor_id) REFERENCES vendors (id)
) engine=InnoDB;

CREATE TABLE orders (
    id INTEGER NOT NULL AUTO_INCREMENT,
    order_list_id INTEGER,
    total_price DECIMAL(10,2) DEFAULT 0,
    ordered_by VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT FK_ORDER_LIST FOREIGN KEY (order_list_id) REFERENCES order_lists (id)
) engine=InnoDB;

CREATE TABLE order_items (
    id INTEGER NOT NULL AUTO_INCREMENT,
    quantity INTEGER NOT NULL DEFAULT 1,
    portion DECIMAL(3,1) NOT NULL DEFAULT 1.0,
    unit_price DECIMAL(10,2) NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    order_id INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_ORDER_ITEM_ORDER FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    CONSTRAINT FK_ORDER_ITEM_PRODUCT FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
) engine=InnoDB;