<%@ page import="java.sql.*" %>
<%@ page import="util.DBConnection" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Appointment Management</title>
    <style>
/* Basic Reset */
body, h1, h2, p, table {
    margin: 0;
    padding: 0;
    font-family: Arial, sans-serif;
}

/* Body Styling */
body {
    background-color: #eaf3fa;
    color: #333;
    padding: 20px;
}

/* Navigation Bar Styling */
nav ul {
    list-style-type: none;
    padding: 0;
    background-color: #007bff;
    margin: 0;
}

nav ul li {
    display: inline-block;
    position: relative;
}

nav ul li a {
    display: block;
    padding: 10px 20px;
    color: white;
    text-decoration: none;
}

nav ul li a:hover {
    background-color: #0056b3;
}

nav ul li ul {
    display: none;
    position: absolute;
    top: 100%;
    left: 0;
    background-color: #007bff;
    padding: 0;
    min-width: 150px;
}

nav ul li:hover > ul {
    display: block;
}

/* Form and Table Styling */
form {
    background-color: white;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    margin-bottom: 30px;
}

form label {
    display: block;
    margin: 8px 0;
}

form input, form textarea, form button {
    width: 100%;
    padding: 10px;
    margin: 8px 0;
    border: 1px solid #ddd;
    border-radius: 4px;
}

form button {
    background-color: #007bff;
    color: white;
    border: none;
    cursor: pointer;
}

form button:hover {
    background-color: #0056b3;
}

table {
    width: 100%;
    margin-top: 20px;
    border-collapse: collapse;
}

table, th, td {
    border: 1px solid #ddd;
}

th, td {
    padding: 12px;
    text-align: left;
}

th {
    background-color: #007bff;
    color: white;
}

/* Message Styling */
p {
    margin-top: 20px;
    font-weight: bold;
}

/* Footer Styling */
footer {
    text-align: center;
    padding: 10px;
    background-color: #007bff;
    color: white;
    margin-top: 30px;
}

footer nav {
    margin-top: 10px;
}

footer nav ul {
    list-style-type: none;
    padding: 0;
}

footer nav ul li {
    display: inline-block;
}

footer nav ul li a {
    color: white;
    text-decoration: none;
    padding: 0 15px;
}

footer nav ul li a:hover {
    text-decoration: underline;
}

/* Media Queries for Responsiveness */
@media screen and (max-width: 768px) {
    /* Navigation Bar */
    nav ul {
        padding: 0;
        text-align: center;
    }

    nav ul li {
        display: block;
        width: 100%;
        margin: 5px 0;
    }

    /* Form and Table */
    form {
        padding: 15px;
        margin-bottom: 20px;
    }

    form input, form textarea, form button {
        width: 100%;
    }

    table {
        font-size: 14px;
        margin-top: 15px;
    }

    th, td {
        padding: 8px;
    }

    /* Footer */
    footer {
        padding: 20px;
    }
}

@media screen and (max-width: 480px) {
    /* Smaller Screen Adjustments */
    h1 {
        font-size: 20px;
    }

    form label {
        font-size: 14px;
    }

    form input, form textarea, form button {
        font-size: 14px;
    }

    table {
        font-size: 12px;
    }

    th, td {
        padding: 6px;
    }
}

    </style>
</head>
<body>
    <!-- Navigation Bar -->
    <nav>
        <ul>
            <li><a href="index.html">Home</a></li>
            <li><a href="appointment_management.jsp">Appointments</a></li>
            <li>
                <a href="#">Management</a>
                <ul>
                    <li><a href="staff_management.html">Staff Management</a></li>
                    <li><a href="patientmanagement.html">Patient Management</a></li>
                    <li><a href="user.html">User Management</a></li>
                </ul>
            </li>
            <li><a href="about.html">About Us</a></li>
            <li><a href="logout.html">Logout</a></li>
        </ul>
    </nav>

    <h1>Manage Appointments</h1>

    <!-- Display Success or Error Message -->
    <%
        String message = request.getParameter("message");
        if (message != null) {
            out.println("<p style='color: red;'>" + message + "</p>");
        }
    %>

    <form action="AppointmentServlet" method="POST">
        <input type="hidden" name="action" value="create">
        <label>User ID:</label><input type="text" name="userID" required><br>
        <label>Senior ID:</label><input type="text" name="seniorID" required><br>
        <label>Caregiver ID:</label><input type="text" name="caregiverID" required><br>
        <label>Date:</label><input type="date" name="appointmentDate" required><br>
        <label>Time:</label><input type="time" name="appointmentTime" required><br>
        <label>Description:</label><textarea name="description" required></textarea><br>
        <label>Status:</label><input type="text" name="status" required><br>
        <button type="submit">Create Appointment</button>
    </form>

    <h2>Appointments List</h2>
<table>
        <tr>
            <th>ID</th>
            <th>User ID</th>
            <th>Senior ID</th>
            <th>Caregiver ID</th>
            <th>Date</th>
            <th>Time</th>
            <th>Description</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        <%
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Appointment");
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getInt("AppointmentID") + "</td>");
                    out.println("<td>" + rs.getInt("UserID") + "</td>");
                    out.println("<td>" + rs.getInt("SeniorID") + "</td>");
                    out.println("<td>" + rs.getInt("CaregiverID") + "</td>");
                    out.println("<td>" + rs.getString("AppointmentDate") + "</td>");
                    out.println("<td>" + rs.getString("AppointmentTime") + "</td>");
                    out.println("<td>" + rs.getString("Description") + "</td>");
                    out.println("<td>" + rs.getString("Status") + "</td>");
                    out.println("<td>");
                    // Update Button
                    out.println("<form action='AppointmentServlet' method='POST' style='display:inline;'>");
                    out.println("<input type='hidden' name='action' value='update'>");
                    out.println("<input type='hidden' name='appointmentID' value='" + rs.getInt("AppointmentID") + "'>");
                    out.println("<button type='submit'>Update</button>");
                    out.println("</form>");
                    // Delete Button
                    out.println("<form action='AppointmentServlet' method='POST' style='display:inline;'>");
                    out.println("<input type='hidden' name='action' value='delete'>");
                    out.println("<input type='hidden' name='appointmentID' value='" + rs.getInt("AppointmentID") + "'>");
                    out.println("<button type='submit'>Delete</button>");
                    out.println("</form>");
                    out.println("</td>");
                    out.println("</tr>");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("<tr><td colspan='9'>Error loading data</td></tr>");
            }
        %>
    </table>
    <footer>
        <p>&copy; 2025 SeniorCare. All rights reserved.</p>
    </footer>
</body>
</html>
