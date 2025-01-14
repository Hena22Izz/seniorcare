<%@ page import="java.sql.*, jakarta.servlet.*, jakarta.servlet.http.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Appointment Management</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h1>Appointment Management</h1>

    <!-- Form to Create or Update Appointment -->
    <h2>Schedule Appointment</h2>
    <form action="AppointmentServlet" method="post">
        <input type="hidden" name="action" value="<%= request.getParameter("action") == null ? "create" : request.getParameter("action") %>" />
        
        <label for="userID">User ID:</label>
        <input type="number" id="userID" name="userID" required /><br>
        
        <label for="seniorID">Senior ID:</label>
        <input type="number" id="seniorID" name="seniorID" required /><br>
        
        <label for="caregiverID">Caregiver ID:</label>
        <input type="number" id="caregiverID" name="caregiverID" required /><br>
        
        <label for="appointmentDate">Appointment Date:</label>
        <input type="date" id="appointmentDate" name="appointmentDate" required /><br>
        
        <label for="appointmentTime">Appointment Time:</label>
        <input type="time" id="appointmentTime" name="appointmentTime" required /><br>
        
        <label for="description">Description:</label>
        <textarea id="description" name="description"></textarea><br>
        
        <label for="status">Status:</label>
        <select id="status" name="status" required>
            <option value="Scheduled">Scheduled</option>
            <option value="Completed">Completed</option>
            <option value="Cancelled">Cancelled</option>
        </select><br>
        
        <input type="submit" value="Submit" />
    </form>

    <!-- List of Appointments -->
    <h2>Existing Appointments</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Appointment ID</th>
                <th>User ID</th>
                <th>Senior ID</th>
                <th>Caregiver ID</th>
                <th>Appointment Date</th>
                <th>Appointment Time</th>
                <th>Description</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% 
                try {
                    // Assuming DBConnection is a utility class to get DB connection
                    Connection conn = DBConnection.getConnection();
                    String sql = "SELECT * FROM Appointment";
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    
                    while (rs.next()) {
                        int appointmentID = rs.getInt("AppointmentID");
                        int userID = rs.getInt("UserID");
                        int seniorID = rs.getInt("SeniorID");
                        int caregiverID = rs.getInt("CaregiverID");
                        String appointmentDate = rs.getString("AppointmentDate");
                        String appointmentTime = rs.getString("AppointmentTime");
                        String description = rs.getString("Description");
                        String status = rs.getString("Status");
            %>
            <tr>
                <td><%= appointmentID %></td>
                <td><%= userID %></td>
                <td><%= seniorID %></td>
                <td><%= caregiverID %></td>
                <td><%= appointmentDate %></td>
                <td><%= appointmentTime %></td>
                <td><%= description %></td>
                <td><%= status %></td>
                <td>
                    <a href="appointment.jsp?action=update&appointmentID=<%= appointmentID %>">Edit</a> |
                    <form action="AppointmentServlet" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="delete" />
                        <input type="hidden" name="appointmentID" value="<%= appointmentID %>" />
                        <input type="submit" value="Delete" />
                    </form>
                </td>
            </tr>
            <% 
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            %>
        </tbody>
    </table>
</body>
</html>
