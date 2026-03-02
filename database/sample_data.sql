INSERT INTO vendors (vendor_id, vendor_name, contact, category) VALUES
(1, 'Alpha Supplies', '9876543210', 'Electronics'),
(2, 'Beta Traders', '9123456780', 'Office Supplies'),
(3, 'Gamma Electronics', '9000000001', 'Electronics'),
(4, 'Delta Office Mart', '9000000002', 'Office Supplies'),
(5, 'Epsilon Tech', '9000000003', 'Electronics'),
(6, 'Zeta Stationers', '9000000004', 'Office Supplies'),
(7, 'Theta Systems', '9000000005', 'Electronics'),
(8, 'Lambda Traders', '9000000006', 'Office Supplies'),
(9, 'Sigma Devices', '9000000007', 'Electronics'),
(10, 'Omega Distributors', '9000000008', 'Office Supplies');

INSERT INTO products (product_id, product_name, vendor_id, unit_price) VALUES
(101, 'Laptop', 1, 60000),
(102, 'Printer', 2, 15000),
(103, 'Paper Pack', 2, 350),
(104, 'Desktop Computer', 1, 45000),
(105, 'Keyboard', 1, 800),
(106, 'Mouse', 1, 500),
(107, 'Monitor', 3, 12000),
(108, 'Router', 3, 3000),
(109, 'Projector', 5, 25000),
(110, 'External Hard Drive', 5, 6000),
(111, 'USB Cable', 5, 200),

(112, 'Office Chair', 4, 5000),
(113, 'Office Desk', 4, 8000),
(114, 'Whiteboard', 4, 2500),
(115, 'Notepad Bundle', 6, 400),
(116, 'Stapler', 6, 250),
(117, 'File Folder Pack', 6, 300),
(118, 'Printer Ink', 2, 1200),
(119, 'Scanner', 2, 9000),

(120, 'Tablet', 7, 20000),
(121, 'Server Rack', 7, 35000),
(122, 'Power Backup UPS', 7, 15000),
(123, 'Networking Switch', 9, 10000),
(124, 'Firewall Device', 9, 40000),
(125, 'SSD 1TB', 9, 7000),
(126, 'Conference Phone', 10, 18000),
(127, 'Paper Shredder', 10, 6000),
(128, 'Laminator', 8, 4500),
(129, 'Marker Pack', 8, 150),
(130, 'Sticky Notes Pack', 8, 120);

INSERT INTO inventory (product_id, stock_qty, reorder_level)
SELECT product_id,
       FLOOR(20 + (RAND() * 180)),   -- stock between 20–200
       FLOOR(10 + (RAND() * 40))     -- reorder level between 10–50
FROM products;

SELECT * FROM vendors;
SELECT * FROM products;
SELECT * FROM inventory;

INSERT INTO purchase_orders (vendor_id, order_date, total_amount)
SELECT 
    FLOOR(1 + (RAND() * 10)),  
    DATE_SUB(CURDATE(), INTERVAL FLOOR(RAND() * 180) DAY),
    FLOOR(5000 + (RAND() * 200000))
FROM information_schema.tables
LIMIT 200;

SELECT COUNT(*) FROM purchase_orders;

INSERT INTO order_items (order_id, product_id, quantity)
SELECT 
    FLOOR(1 + (RAND() * (SELECT MAX(order_id) FROM purchase_orders))),
    FLOOR(101 + (RAND() * 30)),
    FLOOR(1 + (RAND() * 10))
FROM information_schema.tables
LIMIT 600;

UPDATE purchase_orders po
JOIN (
    SELECT 
        oi.order_id,
        SUM(oi.quantity * p.unit_price) AS calculated_total
    FROM order_items oi
    JOIN products p ON oi.product_id = p.product_id
    GROUP BY oi.order_id
) t ON po.order_id = t.order_id
SET po.total_amount = t.calculated_total;
