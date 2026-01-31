INSERT INTO vendors (vendor_id, vendor_name, contact, category) VALUES
(1, 'Alpha Supplies', '9876543210', 'Electronics'),
(2, 'Beta Traders', '9123456780', 'Office Supplies');

INSERT INTO products (product_id, product_name, vendor_id, unit_price) VALUES
(101, 'Laptop', 1, 60000),
(102, 'Printer', 2, 15000),
(103, 'Paper Pack', 2, 350);

INSERT INTO inventory (product_id, stock_qty, reorder_level) VALUES
(101, 4, 3),
(102, 10, 5),
(103, 50, 20);

SELECT * FROM vendors;
SELECT * FROM products;
SELECT * FROM inventory;
