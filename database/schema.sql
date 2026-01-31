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

