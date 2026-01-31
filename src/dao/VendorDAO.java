package dao;

import util.DBConnection;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class VendorDAO {

    public void getAllVendors() {

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM vendors");

            System.out.println("Vendors List:");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("vendor_id") + " | " +
                                rs.getString("vendor_name") + " | " +
                                rs.getString("category")
                );
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}