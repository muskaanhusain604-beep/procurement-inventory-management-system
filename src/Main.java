import dao.PurchaseOrderDAO;
import dao.OrderItemDAO;
import dao.InventoryDAO;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Main {

    public static void main(String[] args) {

        PurchaseOrderDAO poDAO = new PurchaseOrderDAO();
        OrderItemDAO itemDAO = new OrderItemDAO();
        InventoryDAO inventoryDAO = new InventoryDAO();

        Connection con = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false); // Start transaction

            int orderId = poDAO.createPurchaseOrder(con, 1);

            double totalAmount = 0;

            // Item 1
            int qty1 = 5000;
            double price1 = 60000;
            itemDAO.addOrderItem(con, orderId, 101, qty1);
            if (!inventoryDAO.reduceStock(con, 101, qty1)) {
                throw new Exception("Stock failed for product 101");
            }
            totalAmount += qty1 * price1;

            // Item 2
            int qty2 = 3;
            double price2 = 15000;
            itemDAO.addOrderItem(con, orderId, 102, qty2);
            if (!inventoryDAO.reduceStock(con, 102, qty2)) {
                throw new Exception("Stock failed for product 102");
            }
            totalAmount += qty2 * price2;

            // Update total safely
            String updateSql = "UPDATE purchase_orders SET total_amount = ? WHERE order_id = ?";
            try (PreparedStatement ps = con.prepareStatement(updateSql)) {
                ps.setDouble(1, totalAmount);
                ps.setInt(2, orderId);
                ps.executeUpdate();
            }

            con.commit();
            System.out.println("✅ Order placed successfully. Order ID: " + orderId);

        } catch (Exception e) {

            try {
                if (con != null) {
                    con.rollback();
                    System.out.println("❌ Transaction rolled back.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();

        } finally {

            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
