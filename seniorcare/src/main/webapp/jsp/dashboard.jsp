<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" href="../css/styles.css">
    <script src="../js/dashboard.js" defer></script>
</head>
<body>
    <header>
        <h1>Dashboard</h1>
    </header>
    <main>
        <div>
            <h2>Summary</h2>
            <p>Seniors Registered: <span id="seniorCount"><%= request.getAttribute("seniorCount") %></span></p>
            <p>Caregivers Registered: <span id="caregiverCount"><%= request.getAttribute("caregiverCount") %></span></p>
            <p>Appointments: <span id="appointmentCount"><%= request.getAttribute("appointmentCount") %></span></p>
        </div>
    </main>
</body>
</html>
