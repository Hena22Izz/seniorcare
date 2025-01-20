<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Senior Management</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
    <header>
        <h1>Manage Seniors</h1>
    </header>
    <main>
        <form action="SeniorServlet" method="post">
            <input type="hidden" name="action" value="add">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>

            <label for="dob">Date of Birth:</label>
            <input type="date" id="dob" name="dob" required>

            <label for="phone">Phone Number:</label>
            <input type="text" id="phone" name="phone" required>

            <label for="medical">Medical Condition:</label>
            <textarea id="medical" name="medical" rows="4"></textarea>

            <button type="submit">Add Senior</button>
        </form>
    </main>
</body>
</html>
