<%@ page import="java.sql.*" %>
<%
    Connection conn = null;
    try {
        conn = DBConnection.getConnection();
        String query = "SELECT * FROM Appointment";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
%>
<table border="1">
    <tr>
        <th>AppointmentID</th>
        <th>UserID</th>
        <th>SeniorID</th>
        <th>CaregiverID</th>
        <th>Date</th>
        <th>Time</th>
        <th>Description</th>
        <th>Status</th>
    </tr>
    <%
        while (rs.next()) {
    %>
    <tr>
        <td><%= rs.getInt("AppointmentID") %></td>
        <td><%= rs.getInt("UserID") %></td>
        <td><%= rs.getInt("SeniorID") %></td>
        <td><%= rs.getInt("CaregiverID") %></td>
        <td><%= rs.getDate("AppointmentDate") %></td>
        <td><%= rs.getTime("AppointmentTime") %></td>
        <td><%= rs.getString("Description") %></td>
        <td><%= rs.getString("Status") %></td>
    </tr>
    <%
        }
    %>
</table>
<%
    } catch (SQLException e) {
        out.println("Error: " + e.getMessage());
    } finally {
        if (conn != null) conn.close();
    }
%>
