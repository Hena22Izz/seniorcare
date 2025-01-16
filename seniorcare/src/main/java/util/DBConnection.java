package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:1527/seniorcare";
        String username = "app";
        String password = "app";
        return DriverManager.getConnection(url, username, password);
    }
}
