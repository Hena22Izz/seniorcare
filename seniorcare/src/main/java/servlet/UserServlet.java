package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Sambung ke database
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "root", "password")) {
            String query = "SELECT * FROM users";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<h1>Users List</h1>");
                out.println("<table><tr><th>ID</th><th>Username</th><th>Email</th><th>Phone</th><th>Role</th></tr>");
                while (rs.next()) {
                    out.println("<tr><td>" + rs.getInt("id") + "</td><td>" + rs.getString("username") + "</td><td>" + rs.getString("email") + "</td><td>" + rs.getString("phone") + "</td><td>" + rs.getString("role") + "</td></tr>");
                }
                out.println("</table>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
