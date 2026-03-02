Procurement & Inventory Analytics System
Overview

An end-to-end backend system that simulates a real-world procurement and inventory workflow using Java (JDBC) and MySQL. The system implements normalized database design, transaction-safe order processing, and analytical SQL reporting. A business intelligence dashboard layer is added for data visualization.

This project demonstrates backend engineering, database design, ACID compliance, and analytics integration.
Tech Stack
Java (JDBC)
MySQL
SQL (Joins, Aggregations, Window Functions)
Transaction Management (ACID)
Power BI (Analytics Dashboard)

Dataset Scale (Synthetic Business Simulation)
To simulate real-world procurement activity, a synthetic dataset was generated programmatically while maintaining referential integrity.
10 Vendors
30 Products
Inventory tracking for all products
200+ Purchase Orders
600+ Order Items
6 months of transaction history

All purchase order totals are calculated using:
quantity × unit_price
This ensures logical consistency between order items and total spend.

Database Schema

Tables:
 vendors
 products
 inventory
 purchase_orders
 order_items

Key Design Principles:
 Fully normalized schema (3NF)
 Foreign key constraints enforced
 Inventory mapped 1:1 with products
 Orders mapped to vendors
 Order items mapped to both orders and products

Core Features
1. Transaction-Safe Order Processing
   Manual transaction control implemented using:
     setAutoCommit(false)
     commit()
     rollback()

  If any inventory validation fails:
     The entire transaction is rolled back
     No partial order is inserted
     No stock is deducted
This guarantees atomicity and consistency.

2. Inventory Management
Real-time stock deduction during order placement
Stock validation before deduction
Low-stock detection using reorder levels

3. Analytical SQL Reports
Implemented using joins, aggregations, and window functions:
Vendor Spend Report
Product Demand Report
Monthly Purchase Trend
Low Stock Alert
Top Selling Products
Vendor Ranking using RANK()
Product Demand Ranking using DENSE_RANK()
Running Monthly Spend using SUM() OVER()
Stock Risk Ranking
Dashboard Layer

Power BI dashboard built on top of the MySQL database including:
Total Spend KPI
Total Orders KPI
Vendor Spend Ranking
Monthly Spend Trend
Product Demand Analysis
Low Stock Alerts
Running Cumulative Spend
This converts backend data into actionable business insights.

How to Run
Execute schema.sql to create tables.
Execute sample_data.sql to populate base data.
Run Java application (Main.java).
Execute reports.sql for analytical queries.
Connect Power BI to procurement_db for dashboard visualization.

Learning Outcomes
Designing normalized relational schemas
Implementing transaction management in JDBC
Writing analytical SQL with window functions
Maintaining referential integrity
Generating synthetic datasets for realistic simulation

Building end-to-end data flow from backend to dashboard
