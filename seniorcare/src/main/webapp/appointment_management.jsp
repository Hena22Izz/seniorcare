<%@ page import="java.sql.*" %>
<%@ page import="util.DBConnection" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Appointment Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 0;
        }
        h1 {
            text-align: center;
            margin-top: 50px;
            color: #4CAF50;
        }
         nav {
            background-color: #2a2f36;
            overflow: hidden;
        }

        nav ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
        }

        nav li {
            position: relative;
        }

        nav a {
            display: block;
            color: white;
            text-align: center;
            padding: 14px 20px;
            text-decoration: none;
            transition: background 0.3s;
        }

        nav a:hover, nav li:hover > a {
            background-color: #ff6f61;
            color: white;
        }

        /* Dropdown Menu */
        nav ul ul {
            display: none;
            position: absolute;
            background-color: #2a2f36;
            top: 100%;
            left: 0;
            min-width: 150px;
            z-index: 1000;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        nav ul li:hover > ul {
            display: block;
        }

        nav ul ul a {
            padding: 10px 20px;
        }

        nav ul ul a:hover {
            background-color: #ff6f61;
        }

        /* Responsive Navigation */
        @media (max-width: 768px) {
            nav ul {
                flex-direction: column;
                align-items: center;
            }

            nav ul ul {
                position: static;
            }

            nav a {
                padding: 10px;
                text-align: left;
            }
        }

        form {
            width: 50%;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        label {
            font-size: 16px;
            margin-bottom: 8px;
            display: inline-block;
        }
        input[type="text"],
        input[type="date"],
        input[type="time"],
        textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        textarea {
            height: 100px;
            resize: vertical;
        }
        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #45a049;
        }
        table {
            width: 80%;
            margin: 30px auto;
            border-collapse: collapse;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #5ba3d1;
        }
        footer {
            text-align: center;
            padding: 10px;
            background-color: #5ba3d1;
            color: white;
            margin-top: 40px;
        }
    </style>
</head>
<body>
    <!-- Navigation Bar -->
    <nav>
        <ul>
            <li><a href="appointment_management.jsp">Appointments</a></li>
            <li>
                <a href="#">Management</a>
                <ul>
                    <li><a href="staffmanagement.html">Staff Management</a></li>
                    <li><a href="patientmanagement.html">Patient Management</a></li>
                    <li><a href="user.html">User Management</a></li>
                </ul>
            </li>
            <li><a href="about.html">About Us</a></li>
            <li><a href="logout.html">Logout</a></li>
        </ul>
    </nav>

    <h1>Manage Appointments</h1>
    <form action="AppoinmentServlet" method="POST">
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
        </tr>
        <%
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Appoinment");
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getInt("AppoinmentID") + "</td>");
                    out.println("<td>" + rs.getInt("UserID") + "</td>");
                    out.println("<td>" + rs.getInt("SeniorID") + "</td>");
                    out.println("<td>" + rs.getInt("CaregiverID") + "</td>");
                    out.println("<td>" + rs.getString("AppoinmentDate") + "</td>");
                    out.println("<td>" + rs.getString("AppoinmentTime") + "</td>");
                    out.println("<td>" + rs.getString("Description") + "</td>");
                    out.println("<td>" + rs.getString("Status") + "</td>");
                    out.println("</tr>");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("<tr><td colspan='8'>Error loading data</td></tr>");
            }
        %>
    </table>

    <footer>
        <p>&copy; 2025 SeniorCare. All rights reserved.</p>
    </footer>
</body>
</html>
