<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Dashboard</title>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.9.0/chart.min.js"></script>
</head>
<body>
    <h1>Dashboard</h1>

    <h2>Total Data</h2>
    <p>Total Seniors: ${totalSeniors}</p>
    <p>Total Caregivers: ${totalCaregivers}</p>
    <p>Total Appointments: ${totalAppointments}</p>

    <h2>Statistics Chart</h2>
    <canvas id="statsChart" width="400" height="400"></canvas>

    <script>
        const ctx = document.getElementById('statsChart').getContext('2d');
        const chart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['Seniors', 'Caregivers', 'Appointments'],
                datasets: [{
                    label: 'Total Numbers',
                    data: [${totalSeniors}, ${totalCaregivers}, ${totalAppointments}],
                    backgroundColor: ['rgba(255, 99, 132, 0.2)', 'rgba(54, 162, 235, 0.2)', 'rgba(75, 192, 192, 0.2)'],
                    borderColor: ['rgba(255, 99, 132, 1)', 'rgba(54, 162, 235, 1)', 'rgba(75, 192, 192, 1)'],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    </script>
</body>
</html>
