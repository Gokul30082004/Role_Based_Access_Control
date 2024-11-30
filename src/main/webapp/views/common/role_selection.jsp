<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Select Role</title>
</head>
<body>
    <h2>Select a Role</h2>
    <form action="/save_role" method="post">
        <label for="email">Email: </label>
        <input type="text" name="email" value="${email}" readonly /><br><br>

        <label for="role">Role: </label>
        <select name="role" required>
            <option value="ROLE_USER">User</option>
            <option value="ROLE_ADMIN">Admin</option>
        </select><br><br>

        <button type="submit">Save Role</button>
    </form>
</body>
</html>
