package dao;

import util.DBConnection;
import java.sql.*;

public class InventoryDAO {

    public boolean reduceStock(int productId, int quantity) {

        String checkSql = "SELECT stock_qty FROM inventory WHERE product_id = ?";
        String updateSql = "UPDATE inventory SET stock_qty = stock_qty - ? WHERE product_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement checkPs = con.prepareStatement(checkSql)) {

            checkPs.setInt(1, productId);
            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {
                int availableStock = rs.getInt("stock_qty");

                if (availableStock < quantity) {
                    System.out.println(
                            "❌ Not enough stock for product " + productId +
                                    ". Available: " + availableStock
                    );
                    return false;
                }
            }

            try (PreparedStatement updatePs = con.prepareStatement(updateSql)) {
                updatePs.setInt(1, quantity);
                updatePs.setInt(2, productId);
                updatePs.executeUpdate();
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
