package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import util.DBConnection;

@WebServlet("/AppointmentServlet")
public class AppointmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM Appointment";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            request.setAttribute("appointments", rs);
            request.getRequestDispatcher("appointment_management.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try (Connection conn = DBConnection.getConnection()) {
            if ("create".equals(action)) {
                String sql = "INSERT INTO Appointment (UserID, SeniorID, CaregiverID, AppointmentDate, AppointmentTime, Description, Status) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, Integer.parseInt(request.getParameter("UserID")));
                    ps.setInt(2, Integer.parseInt(request.getParameter("SeniorID")));
                    ps.setInt(3, Integer.parseInt(request.getParameter("CaregiverID")));
                    ps.setDate(4, Date.valueOf(request.getParameter("AppointmentDate")));
                    ps.setTime(5, Time.valueOf(request.getParameter("AppointmentTime")));
                    ps.setString(6, request.getParameter("Description"));
                    ps.setString(7, request.getParameter("Status"));
                    ps.executeUpdate();
                }
            } else if ("update".equals(action)) {
                String sql = "UPDATE Appointment SET Status = ? WHERE AppointmentID = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, request.getParameter("Status"));
                    ps.setInt(2, Integer.parseInt(request.getParameter("AppointmentID")));
                    ps.executeUpdate();
                }
            } else if ("delete".equals(action)) {
                String sql = "DELETE FROM Appointment WHERE AppointmentID = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, Integer.parseInt(request.getParameter("AppointmentID")));
                    ps.executeUpdate();
                }
            }
            response.sendRedirect("appointment_management.jsp");
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
}
