<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Signup</title>
</head>
<body>
    <h1>Signup</h1>

    <c:if test="${not empty message}">
        <p style="color: red;">${message}</p>
    </c:if>

    <form action="/signup" method="post">

        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email" value="${user.email}" required><br><br>

        <label for="role">Role:</label><br>
         <select id="role" name="role" required>
            <option value="ROLE_USER">User</option>
            <option value="ROLE_ADMIN">Admin</option>
        </select><br><br>

        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password" required><br><br>

        <label for="confirmPassword">Confirm Password:</label><br>
        <input type="password" id="confirmPassword" name="confirmPassword" required><br><br>

        <button type="submit">Signup</button>
    </form>

    <br><br>
    <a href="/login">Already have an account? Login here.</a><br>
    <a href="/">Back to Home</a>
</body>
</html>
