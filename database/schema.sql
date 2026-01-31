USE procurement_db;
CREATE DATABASE procurement_db;

CREATE TABLE vendors (
  vendor_id INT PRIMARY KEY,
  vendor_name VARCHAR(100) NOT NULL,
  contact VARCHAR(50),
  category VARCHAR(50)
);

CREATE TABLE products (
  product_id INT PRIMARY KEY,
  product_name VARCHAR(100) NOT NULL,
  vendor_id INT,
  unit_price DECIMAL(10,2),
  FOREIGN KEY (vendor_id) REFERENCES vendors(vendor_id)
);

CREATE TABLE inventory (
  product_id INT PRIMARY KEY,
  stock_qty INT NOT NULL,
  reorder_level INT NOT NULL,
  FOREIGN KEY (product_id) REFERENCES products(product_id)
);
SHOW TABLES;

CREATE TABLE purchase_orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    vendor_id INT,
    order_date DATE,
    total_amount DECIMAL(10,2),
    FOREIGN KEY (vendor_id) REFERENCES vendors(vendor_id)
);

CREATE TABLE order_items (
    order_item_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT,
    product_id INT,
    quantity INT,
    FOREIGN KEY (order_id) REFERENCES purchase_orders(order_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

SHOW TABLES;

SELECT * FROM purchase_orders;
SELECT * FROM order_items;

SELECT product_id, product_name FROM products;
SELECT product_id, stock_qty FROM inventory;

SELECT product_id, stock_qty FROM inventory
WHERE product_id IN (101, 102, 103);

SELECT product_id, stock_qty
FROM inventory
WHERE product_id IN (101, 102, 103);

UPDATE inventory SET stock_qty = 10 WHERE product_id = 101;

