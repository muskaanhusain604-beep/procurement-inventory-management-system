Procurement/Inventory Management System

Overview
This project is an end-to-end Procurement and Inventory Analytics System built using Java (JDBC) and MySQL. It simulates a real-world procurement workflow including vendor management, product cataloging, inventory tracking, and purchase order processing. The system is designed with a fully normalized relational schema and focuses on backend engineering principles such as data integrity, transaction management, and analytical reporting.

System Design
The database consists of five core tables: vendors, products, inventory, purchase_orders, and order_items. The schema follows normalization principles (3NF) with proper foreign key relationships to ensure referential integrity. Inventory is mapped one-to-one with products, while purchase orders are linked to vendors and order items are linked to both products and orders.

Dataset Scale
To simulate realistic business operations, synthetic data was generated programmatically. The system currently includes 10 vendors, 30 products, inventory records for all products, 200+ purchase orders, 600+ order items, and approximately 6 months of transactional data. All purchase order totals are calculated using quantity × unit_price to maintain logical consistency across the system.

Transaction Management
Order placement is implemented using manual transaction control with setAutoCommit(false), commit(), and rollback(). If inventory validation fails at any stage, the entire transaction is rolled back to prevent partial order insertion or inconsistent stock updates. This ensures atomicity and consistency, demonstrating ACID compliance.

Analytical Reporting
The project includes advanced SQL reports using joins, aggregations, and window functions. Reports include vendor spend analysis, product demand ranking, monthly purchase trends, running cumulative spend, low-stock alerts, vendor ranking using RANK(), product demand ranking using DENSE_RANK(), and stock risk ranking. These queries demonstrate strong SQL proficiency beyond basic CRUD operations.

Dashboard Integration
A Power BI dashboard is built on top of the MySQL database to visualize key performance indicators such as total spend, total orders, vendor ranking, product demand trends, and low-stock alerts. This adds a business intelligence layer to the backend system and transforms transactional data into actionable insights.

Key Takeaways
This project demonstrates backend development using JDBC, normalized database design, transaction-safe processing, analytical SQL with window functions, synthetic data simulation, and integration of backend systems with a BI dashboard for end-to-end data visualization.
