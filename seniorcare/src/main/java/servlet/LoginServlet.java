package servlet;

import dao.LoginDao;
import util.User;
import util.Role;
import util.DBConnection;
import java.io.IOException;
import jakarta.servlet.ServletException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDao loginDao;

    @Override
    public void init() {
        loginDao = new LoginDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT users.*, roles.role_name FROM users JOIN roles ON users.role_id = roles.role_id WHERE username = ? AND password = ?")) {
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Role role = new Role(rs.getInt("role_id"), rs.getString("role_name"));
                User user = new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"), role);

                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                response.sendRedirect("staff_management.html");
            } else {
                response.sendRedirect("login.jsp?error=true");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=true");
        }
    }
}