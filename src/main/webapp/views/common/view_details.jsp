<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Details</title>
</head>
<body>
    <h1>View User Details</h1>

    <c:if test="${not empty message}">
        <p style="color: red;">${message}</p>
    </c:if>

    <table border="1">
        <thead>
            <tr>
                <th>Email</th>
                <th>Role</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>${email}</td>
                <td>${role}</td>
            </tr>
        </tbody>
    </table>

    <br>
    <a href="/welcome">Back to Welcome Page</a> |
    <a href="/update_password">Change Password</a>
</body>
</html>
