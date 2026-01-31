#sql report 1
#vendor spend report
SELECT v.vendor_name, SUM(po.total_amount) AS total_spent
FROM purchase_orders po
JOIN vendors v ON po.vendor_id = v.vendor_id
GROUP BY v.vendor_name;

#report 2
#Product demand report
SELECT p.product_name, SUM(oi.quantity) AS total_quantity_ordered
FROM order_items oi
JOIN products p ON oi.product_id = p.product_id
GROUP BY p.product_name
ORDER BY total_quantity_ordered DESC;

#report 3 monthly purchase trend 
SELECT 
    MONTH(order_date) AS month,
    SUM(total_amount) AS monthly_spend
FROM purchase_orders
GROUP BY MONTH(order_date)
ORDER BY month;

#report low stock alert report
SELECT 
    p.product_name,
    i.stock_qty
FROM inventory i
JOIN products p ON i.product_id = p.product_id
WHERE i.stock_qty < 5
ORDER BY i.stock_qty;

#report 5 top selling products
SELECT 
    p.product_name,
    SUM(oi.quantity) AS total_quantity_ordered
FROM order_items oi
JOIN products p ON oi.product_id = p.product_id
GROUP BY p.product_name
ORDER BY total_quantity_ordered DESC;

#window functions top vendors by monthly spend
SELECT 
    vendor_name,
    total_spent,
    RANK() OVER (ORDER BY total_spent DESC) AS vendor_rank
FROM (
    SELECT 
        v.vendor_name,
        SUM(po.total_amount) AS total_spent
    FROM purchase_orders po
    JOIN vendors v ON po.vendor_id = v.vendor_id
    GROUP BY v.vendor_name
) t;

#rank products by demand
SELECT 
    product_name,
    total_quantity_ordered,
    DENSE_RANK() OVER (ORDER BY total_quantity_ordered DESC) AS demand_rank
FROM (
    SELECT 
        p.product_name,
        SUM(oi.quantity) AS total_quantity_ordered
    FROM order_items oi
    JOIN products p ON oi.product_id = p.product_id
    GROUP BY p.product_name
) t;

#running total of monthly spend
SELECT
    month,
    monthly_spend,
    SUM(monthly_spend) OVER (ORDER BY month) AS running_total_spend
FROM (
    SELECT
        MONTH(order_date) AS month,
        SUM(total_amount) AS monthly_spend
    FROM purchase_orders
    GROUP BY MONTH(order_date)
) t;

#rank products by stock level
SELECT
    p.product_name,
    i.stock_qty,
    RANK() OVER (ORDER BY i.stock_qty ASC) AS stock_risk_rank
FROM inventory i
JOIN products p ON i.product_id = p.product_id;
