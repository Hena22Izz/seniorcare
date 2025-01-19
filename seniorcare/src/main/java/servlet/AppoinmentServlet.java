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

@WebServlet("/AppoinmentServlet")
public class AppoinmentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt;

            switch (action) {
                case "create":
                    stmt = conn.prepareStatement("INSERT INTO Appoinment (UserID, SeniorID, CaregiverID, AppoinmentDate, AppoinmentTime, Description, Status) VALUES (?, ?, ?, ?, ?, ?, ?)");
                    stmt.setInt(1, Integer.parseInt(request.getParameter("userID")));
                    stmt.setInt(2, Integer.parseInt(request.getParameter("seniorID")));
                    stmt.setInt(3, Integer.parseInt(request.getParameter("caregiverID")));
                    stmt.setString(4, request.getParameter("appointmentDate"));
                    stmt.setString(5, request.getParameter("appointmentTime"));
                    stmt.setString(6, request.getParameter("description"));
                    stmt.setString(7, request.getParameter("status"));
                    stmt.executeUpdate();
                    response.getWriter().println("Appointment created successfully!");
                    break;

                case "update":
                    stmt = conn.prepareStatement("UPDATE Appoinment SET UserID=?, SeniorID=?, CaregiverID=?, AppoinmentDate=?, AppoinmentTime=?, Description=?, Status=? WHERE AppoinmentID=?");
                    stmt.setInt(1, Integer.parseInt(request.getParameter("userID")));
                    stmt.setInt(2, Integer.parseInt(request.getParameter("seniorID")));
                    stmt.setInt(3, Integer.parseInt(request.getParameter("caregiverID")));
                    stmt.setString(4, request.getParameter("appointmentDate"));
                    stmt.setString(5, request.getParameter("appointmentTime"));
                    stmt.setString(6, request.getParameter("description"));
                    stmt.setString(7, request.getParameter("status"));
                    stmt.setInt(8, Integer.parseInt(request.getParameter("appointmentID")));
                    stmt.executeUpdate();
                    response.getWriter().println("Appointment updated successfully!");
                    break;

                case "delete":
                    stmt = conn.prepareStatement("DELETE FROM Appoinment WHERE AppoinmentID=?");
                    stmt.setInt(1, Integer.parseInt(request.getParameter("appointmentID")));
                    stmt.executeUpdate();
                    response.getWriter().println("Appointment deleted successfully!");
                    break;

                default:
                    response.getWriter().println("Invalid action!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("An error occurred: " + e.getMessage());
        }
    }
}
