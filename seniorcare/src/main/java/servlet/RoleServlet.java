package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.DBConnection;

// Map this servlet to /RoleServlet
@WebServlet("/RoleServlet")
public class RoleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handles HTTP GET requests (e.g., to display roles)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DBConnection.getConnection()) {
            // Fetch roles from the database
            List<String> roles = getRolesFromDatabase(conn);

            // Set roles as a request attribute
            request.setAttribute("roles", roles);

            // Forward to JSP page to display roles
            RequestDispatcher dispatcher = request.getRequestDispatcher("role.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }

    // Handles HTTP POST requests (e.g., to add a new role)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roleName = request.getParameter("roleName");

        try (Connection conn = DBConnection.getConnection()) {
            // Add the new role to the database
            addRoleToDatabase(conn, roleName);

            // Redirect to the same servlet to refresh the roles list
            response.sendRedirect("RoleServlet");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to add role");
        }
    }

    // Helper method to fetch roles from the database
    private List<String> getRolesFromDatabase(Connection conn) throws SQLException {
        List<String> roles = new ArrayList<>();
        String query = "SELECT Role_Name FROM Role";
        try (PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                roles.add(rs.getString("Role_Name"));
            }
        }
        return roles;
    }

    // Helper method to add a role to the database
    private void addRoleToDatabase(Connection conn, String roleName) throws SQLException {
        String query = "INSERT INTO Role (Role_Name) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, roleName);
            stmt.executeUpdate();
        }
    }
}
