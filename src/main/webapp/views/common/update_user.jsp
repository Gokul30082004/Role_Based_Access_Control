<%@ page language = "java" contentType = "text/html; charset = UTF-8"
    pageEncoding = "UTF-8" isELIgnored = "false"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update User</title>
</head>
<body>
    <h1>Update User Details</h1>

    <c:if test="${not empty message}">
        <p style="color: red;">${message}</p>
    </c:if>

    <form action="/admin/update_user" method="post">
        <label for="email">Email:</label>
        <input type="email" name="email" required>
        <input type="submit" value="Search">
    </form>

    <c:if test="${not empty user}">
        <h2>Edit User Details</h2>
        <form action="/admin/save_user" method="post">
            <input type="hidden" name="id" value="${user.id}">
            <label for="email">Email:</label>
            <input type="email" name="email" value="${user.email}" readonly><br><br>


            <label for="password">Password:</label>
            <input type="password" name="password" required><br><br>

            <label for="role">Role:</label>
                <select name="role" required>
                    <option value="ADMIN" ${user.role == 'ADMIN' ? 'selected' : ''}>Admin</option>
                    <option value="USER" ${user.role == 'USER' ? 'selected' : ''}>User</option>
                </select><br><br>
            <input type="submit" value="Save Changes">
        </form>
    </c:if>

    <a href="/welcome">Welcome page</a>

</body>
</html>
