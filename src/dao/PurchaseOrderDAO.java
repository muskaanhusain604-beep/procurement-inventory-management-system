package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PurchaseOrderDAO {

    public int createPurchaseOrder(Connection con, int vendorId) throws SQLException {

        String sql = "INSERT INTO purchase_orders (vendor_id, order_date, total_amount) VALUES (?, ?, ?)";

        int orderId = 0;

        try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, vendorId);
            ps.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            ps.setDouble(3, 0.0); // temporary, will calculate later

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }
        }

        return orderId;
    }
}
