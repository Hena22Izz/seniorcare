import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class AppointmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try (Connection conn = DBConnection.getConnection()) {
            if ("create".equalsIgnoreCase(action)) {
                createAppointment(request, conn);
            } else if ("update".equalsIgnoreCase(action)) {
                updateAppointment(request, conn);
            } else if ("delete".equalsIgnoreCase(action)) {
                deleteAppointment(request, conn);
            }
            response.sendRedirect("appointment.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createAppointment(HttpServletRequest request, Connection conn) throws SQLException {
        int userID = Integer.parseInt(request.getParameter("userID"));
        int seniorID = Integer.parseInt(request.getParameter("seniorID"));
        int caregiverID = Integer.parseInt(request.getParameter("caregiverID"));
        String appointmentDate = request.getParameter("appointmentDate");
        String appointmentTime = request.getParameter("appointmentTime");
        String description = request.getParameter("description");
        String status = request.getParameter("status");

        String sql = "INSERT INTO Appointment (UserID, SeniorID, CaregiverID, AppointmentDate, AppointmentTime, Description, Status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            stmt.setInt(2, seniorID);
            stmt.setInt(3, caregiverID);
            stmt.setString(4, appointmentDate);
            stmt.setString(5, appointmentTime);
            stmt.setString(6, description);
            stmt.setString(7, status);
            stmt.executeUpdate();
        }
    }

    private void updateAppointment(HttpServletRequest request, Connection conn) throws SQLException {
        int appointmentID = Integer.parseInt(request.getParameter("appointmentID"));
        int userID = Integer.parseInt(request.getParameter("userID"));
        int seniorID = Integer.parseInt(request.getParameter("seniorID"));
        int caregiverID = Integer.parseInt(request.getParameter("caregiverID"));
        String appointmentDate = request.getParameter("appointmentDate");
        String appointmentTime = request.getParameter("appointmentTime");
        String description = request.getParameter("description");
        String status = request.getParameter("status");

        String sql = "UPDATE Appointment SET UserID = ?, SeniorID = ?, CaregiverID = ?, AppointmentDate = ?, "
                + "AppointmentTime = ?, Description = ?, Status = ? WHERE AppointmentID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            stmt.setInt(2, seniorID);
            stmt.setInt(3, caregiverID);
            stmt.setString(4, appointmentDate);
            stmt.setString(5, appointmentTime);
            stmt.setString(6, description);
            stmt.setString(7, status);
            stmt.setInt(8, appointmentID);
            stmt.executeUpdate();
        }
    }

    private void deleteAppointment(HttpServletRequest request, Connection conn) throws SQLException {
        int appointmentID = Integer.parseInt(request.getParameter("appointmentID"));

        String sql = "DELETE FROM Appointment WHERE AppointmentID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, appointmentID);
            stmt.executeUpdate();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
