package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryDAO {

    public boolean reduceStock(Connection con, int productId, int quantity) throws SQLException {

        String checkSql = "SELECT stock_qty FROM inventory WHERE product_id = ?";
        String updateSql = "UPDATE inventory SET stock_qty = stock_qty - ? WHERE product_id = ?";

        // Check available stock
        try (PreparedStatement checkPs = con.prepareStatement(checkSql)) {

            checkPs.setInt(1, productId);
            ResultSet rs = checkPs.executeQuery();

            if (!rs.next()) {
                throw new SQLException("Product not found in inventory: " + productId);
            }

            int availableStock = rs.getInt("stock_qty");

            if (availableStock < quantity) {
                System.out.println("Not enough stock for product " + productId +
                        ". Available: " + availableStock);
                return false;
            }
        }

        // Reduce stock
        try (PreparedStatement updatePs = con.prepareStatement(updateSql)) {

            updatePs.setInt(1, quantity);
            updatePs.setInt(2, productId);
            updatePs.executeUpdate();
        }

        return true;
    }
}
