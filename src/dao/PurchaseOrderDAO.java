package dao;

import util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class PurchaseOrderDAO {

    public int createPurchaseOrder(int vendorId, double totalAmount) {

        int orderId = 0;

        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO purchase_orders (vendor_id, order_date, total_amount) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setInt(1, vendorId);
            ps.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            ps.setDouble(3, totalAmount);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderId;
    }
}
