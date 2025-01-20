package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/AppointmentServlet")
public class AppointmentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        // Set response content type
        response.setContentType("text/html");

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt;
            ResultSet rs;

            switch (action) {
                case "create":
                    // Check if SeniorID exists
                    stmt = conn.prepareStatement("SELECT COUNT(*) FROM Senior WHERE SeniorID = ?");
                    stmt.setInt(1, Integer.parseInt(request.getParameter("seniorID")));
                    rs = stmt.executeQuery();
                    if (rs.next() && rs.getInt(1) > 0) {
                        // SeniorID exists, proceed with insert
                        stmt = conn.prepareStatement("INSERT INTO Appointment (UserID, SeniorID, CaregiverID, AppointmentDate, AppointmentTime, Description, Status) VALUES (?, ?, ?, ?, ?, ?, ?)");
                        stmt.setInt(1, Integer.parseInt(request.getParameter("userID")));
                        stmt.setInt(2, Integer.parseInt(request.getParameter("seniorID")));
                        stmt.setInt(3, Integer.parseInt(request.getParameter("caregiverID")));
                        stmt.setString(4, request.getParameter("appointmentDate"));
                        stmt.setString(5, request.getParameter("appointmentTime"));
                        stmt.setString(6, request.getParameter("description"));
                        stmt.setString(7, request.getParameter("status"));
                        stmt.executeUpdate();

                        // Redirect back to the JSP page with success message
                        response.sendRedirect("appointment_management.jsp?message=Appointment created successfully!");
                    } else {
                        // SeniorID does not exist
                        response.sendRedirect("appointment_management.jsp?message=SeniorID does not exist.");
                    }
                    break;

                case "update":
                    // Validate required parameters before proceeding
                    String appointmentID = request.getParameter("appointmentID");
                    String userID = request.getParameter("userID");
                    String seniorID = request.getParameter("seniorID");
                    String caregiverID = request.getParameter("caregiverID");
                    String appointmentDate = request.getParameter("appointmentDate");
                    String appointmentTime = request.getParameter("appointmentTime");
                    String description = request.getParameter("description");
                    String status = request.getParameter("status");

                    if (appointmentID == null || userID == null || seniorID == null || caregiverID == null ||
                        appointmentDate == null || appointmentTime == null || description == null || status == null) {
                        response.sendRedirect("appointment_management.jsp?message=Missing required fields.");
                        return;
                    }

                    stmt = conn.prepareStatement("UPDATE Appointment SET UserID=?, SeniorID=?, CaregiverID=?, AppointmentDate=?, AppointmentTime=?, Description=?, Status=? WHERE AppointmentID=?");
                    stmt.setInt(1, Integer.parseInt(userID));
                    stmt.setInt(2, Integer.parseInt(seniorID));
                    stmt.setInt(3, Integer.parseInt(caregiverID));
                    stmt.setString(4, appointmentDate);
                    stmt.setString(5, appointmentTime);
                    stmt.setString(6, description);
                    stmt.setString(7, status);
                    stmt.setInt(8, Integer.parseInt(appointmentID));
                    stmt.executeUpdate();
                    response.sendRedirect("appointment_management.jsp?message=Appointment updated successfully!");
                    break;

                case "delete":
                    stmt = conn.prepareStatement("DELETE FROM Appointment WHERE AppointmentID=?");
                    stmt.setInt(1, Integer.parseInt(request.getParameter("appointmentID")));
                    stmt.executeUpdate();
                    response.sendRedirect("appointment_management.jsp?message=Appointment deleted successfully!");
                    break;

                default:
                    response.getWriter().println("Invalid action!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("appointment_management.jsp?message=An error occurred: " + e.getMessage());
        }
    }
}
