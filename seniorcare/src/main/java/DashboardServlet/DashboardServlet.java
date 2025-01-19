import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        int seniorCount = 0, caregiverCount = 0, appointmentCount = 0;

        try {
            // Database connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/seniorcare", "root", "password");

            // Query for seniors count
            stmt = conn.prepareStatement("SELECT COUNT(*) AS count FROM seniors");
            rs = stmt.executeQuery();
            if (rs.next()) seniorCount = rs.getInt("count");

            // Query for caregivers count
            stmt = conn.prepareStatement("SELECT COUNT(*) AS count FROM caregivers");
            rs = stmt.executeQuery();
            if (rs.next()) caregiverCount = rs.getInt("count");

            // Query for appointments count
            stmt = conn.prepareStatement("SELECT COUNT(*) AS count FROM appointments");
            rs = stmt.executeQuery();
            if (rs.next()) appointmentCount = rs.getInt("count");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException ignored) {}
            try { if (stmt != null) stmt.close(); } catch (SQLException ignored) {}
            try { if (conn != null) conn.close(); } catch (SQLException ignored) {}
        }

        // Set attributes for JSP
        request.setAttribute("seniorCount", seniorCount);
        request.setAttribute("caregiverCount", caregiverCount);
        request.setAttribute("appointmentCount", appointmentCount);

        // Forward to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
