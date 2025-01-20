<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Caregiver Management</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
    <header>
        <h1>Manage Caregivers</h1>
    </header>
    <main>
        <form action="CaregiverServlet" method="post">
            <input type="hidden" name="action" value="add">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>

            <label for="specialty">Specialty:</label>
            <input type="text" id="specialty" name="specialty" required>

            <label for="phone">Phone Number:</label>
            <input type="text" id="phone" name="phone" required>

            <button type="submit">Add Caregiver</button>
        </form>
    </main>
</body>
</html>
