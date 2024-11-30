<%@ page language = "java" contentType = "text/html; charset = UTF-8"
    pageEncoding = "UTF-8" isELIgnored = "false"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create User</title>
</head>
<body>

<h2>Create New User</h2>

<c:if test="${not empty message}">
    <div style="color: green;">${message}</div>
</c:if>
<c:if test="${not empty error}">
    <div style="color: red;">${error}</div>
</c:if>

<form action="/admin/create_user" method="POST">
    <label for="email">Email:</label><br>
    <input type="email" id="email" name="email" value="${user.email}" required><br><br>

    <label for="password">Password:</label><br>
    <input type="password" id="password" name="password" required><br><br>

    <label for="role">Role:</label><br>
    <select id="role" name="role">
        <option value="ROLE_USER">User</option>
        <option value="ROLE_ADMIN">Admin</option>
    </select><br><br>

    <button type="submit">Create User</button>

    <a href="/welcome">Welcome page</a>
</form>

</body>
</html>
