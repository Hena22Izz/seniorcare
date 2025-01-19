package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:1527/seniorcare";
    private static final String USER = "app";
    private static final String PASSWORD = "app"; 

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Database driver not found.");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
