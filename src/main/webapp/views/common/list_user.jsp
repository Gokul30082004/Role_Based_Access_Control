<%@ page language = "java" contentType = "text/html; charset = UTF-8"
    pageEncoding = "UTF-8" isELIgnored = "false"%>
 <%@ page import="com.example.projectrbac.model.Users" %>
 <%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>List of Users</title>
</head>
<body>
    <h1>List of Users</h1>

    <c:if test="${not empty message}">
        <p style="color: green;">${message}</p>
    </c:if>

    <table border="1">
        <thead>
            <tr>
                <th>Email</th>
                <th>Role</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>

            <tbody>
                <%
                    List<Users> usersList = (List<Users>) request.getAttribute("users");
                    if (usersList != null) {
                        for (Users user : usersList) {
                %>
                <tr>
                    <td><%= user.getEmail() %></td>
                    <td><%= user.getRole() %></td>
                    <td>
                        <a href="/admin/update_user?email=<%= user.getEmail() %>">View</a> |
                        <a href="/admin/delete_user?email=<%= user.getEmail() %>">Delete</a>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </tbody>
    </table>

    <br><br>
    <a href="/admin/create_user">Create New User</a>
    <a href="/welcome">Welcome page</a>
</body>
</html>
