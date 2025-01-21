package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ambil data dari form
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String role = request.getParameter("role");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");

        // Validasi kata laluan
        if (!password.equals(confirmPassword)) {
            response.getWriter().println("Passwords do not match.");
            return;
        }

        // Sambung ke pangkalan data (gunakan JDBC)
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "root", "password")) {
            // Masukkan pengguna ke dalam pangkalan data
            String query = "INSERT INTO users (username, email, phone, role, password) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                stmt.setString(2, email);
                stmt.setString(3, phone);
                stmt.setString(4, role);
                stmt.setString(5, password);  // Ingat untuk hash password dalam aplikasi sebenar!
                stmt.executeUpdate();
                response.getWriter().println("Registration successful!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
