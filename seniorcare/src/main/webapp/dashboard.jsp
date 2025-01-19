<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard | SeniorCare</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f3f8fa;
            color: #333;
        }

        header {
            background: linear-gradient(90deg, #5ba3d1, #ff6f61);
            color: white;
            padding: 20px;
            text-align: center;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
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

        nav a {
            color: white;
            text-align: center;
            padding: 14px 20px;
            text-decoration: none;
            transition: background 0.3s;
        }

        nav a:hover {
            background-color: #ff6f61;
        }

        .container {
            padding: 20px 40px;
        }

        h2 {
            text-align: center;
            color: #5ba3d1;
        }

        .chart-container {
            display: flex;
            justify-content: center;
            flex-wrap: wrap;
            gap: 20px;
        }

        canvas {
            max-width: 300px;
            margin: 20px auto;
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }

        footer {
            text-align: center;
            padding: 10px;
            background-color: #5ba3d1;
            color: white;
            margin-top: 40px;
        }
    </style>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <header>
        <h1>SeniorCare Dashboard</h1>
    </header>

    <nav>
        <ul>
            <li><a href="patient_management.html">Senior Management</a></li>
            <li><a href="caregiver.html">Caregiver Management</a></li>
            <li><a href="dashboard.jsp">Dashboard</a></li>
            <li><a href="logout.html">Logout</a></li>
        </ul>
    </nav>

    <div class="container">
        <h2>Data Overview</h2>
        <div class="chart-container">
            <canvas id="seniorChart"></canvas>
            <canvas id="caregiverChart"></canvas>
            <canvas id="appointmentChart"></canvas>
        </div>
    </div>

    <footer>
        <p>&copy; 2025 SeniorCare. All rights reserved.</p>
    </footer>

    <script>
        const seniorCount = <%= request.getAttribute("seniorCount") %>;
        const caregiverCount = <%= request.getAttribute("caregiverCount") %>;
        const appointmentCount = <%= request.getAttribute("appointmentCount") %>;

        const ctx1 = document.getElementById('seniorChart').getContext('2d');
        new Chart(ctx1, {
            type: 'pie',
            data: {
                labels: ['Seniors'],
                datasets: [{
                    data: [seniorCount],
                    backgroundColor: ['#5ba3d1']
                }]
            },
        });

        const ctx2 = document.getElementById('caregiverChart').getContext('2d');
        new Chart(ctx2, {
            type: 'pie',
            data: {
                labels: ['Caregivers'],
                datasets: [{
                    data: [caregiverCount],
                    backgroundColor: ['#ff6f61']
                }]
            },
        });

        const ctx3 = document.getElementById('appointmentChart').getContext('2d');
        new Chart(ctx3, {
            type: 'pie',
            data: {
                labels: ['Appointments'],
                datasets: [{
                    data: [appointmentCount],
                    backgroundColor: ['#4CAF50']
                }]
            },
        });
    </script>
</body>
</html>
