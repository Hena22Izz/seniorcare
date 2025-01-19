<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Logout | Senior Care Coordination System</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background: linear-gradient(to right, #3498db, #8e44ad);
        }

        .container {
            text-align: center;
            background-color: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #333;
        }

        .message {
            color: #2ecc71;
            font-size: 18px;
            margin-bottom: 20px;
        }

        .login-btn {
            display: inline-block;
            padding: 12px 24px;
            background-color: #3498db;
            color: white;
            text-decoration: none;
            font-size: 18px;
            border-radius: 5px;
            cursor: pointer;
            transition: background 0.3s ease;
        }

        .login-btn:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>Successfully Logged Out</h1>
        <p class="message">You have been logged out successfully.</p>

        <!-- Login button to go back to login page -->
        <a href="login.jsp" class="login-btn">Login Again</a>
    </div>

</body>
</html>
