package dao;

import util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class OrderItemDAO {

    public void addOrderItem(int orderId, int productId, int quantity) {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO order_items (order_id, product_id, quantity) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, orderId);
            ps.setInt(2, productId);
            ps.setInt(3, quantity);

            ps.executeUpdate();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
