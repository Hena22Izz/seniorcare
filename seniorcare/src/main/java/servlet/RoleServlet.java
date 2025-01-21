package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class RoleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Sambung ke database dan paparkan senarai peranan
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "root", "password")) {
            String query = "SELECT * FROM roles";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<h1>Roles List</h1>");
                out.println("<table><tr><th>ID</th><th>Role Name</th></tr>");
                while (rs.next()) {
                    out.println("<tr><td>" + rs.getInt("id") + "</td><td>" + rs.getString("role_name") + "</td></tr>");
                }
                out.println("</table>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
