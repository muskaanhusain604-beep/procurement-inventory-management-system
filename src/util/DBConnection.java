package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() throws Exception {

        String url = "jdbc:mysql://localhost:3306/procurement_db";
        String user = "root";
        String password = "muskaan@123"; // same as before

        return DriverManager.getConnection(url, user, password);
    }
}