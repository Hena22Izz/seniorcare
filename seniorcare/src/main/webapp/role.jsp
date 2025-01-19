<!DOCTYPE html>
<html>
<head>
    <title>Role Management</title>
</head>
<body>
    <h1>Role Management</h1>

    <!-- Display the list of roles -->
    <h2>Existing Roles</h2>
    <ul>
        <c:forEach var="role" items="${roles}">
            <li>${role}</li>
        </c:forEach>
    </ul>

    <!-- Form to add a new role -->
    <h2>Add a New Role</h2>
    <form action="RoleServlet" method="post">
        <label for="roleName">Role Name:</label>
        <input type="text" id="roleName" name="roleName" required>
        <button type="submit">Add Role</button>
    </form>
</body>
</html>
