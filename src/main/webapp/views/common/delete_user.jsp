<%@ page language = "java" contentType = "text/html; charset = UTF-8"
    pageEncoding = "UTF-8" isELIgnored = "false"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delete User</title>
</head>
<body>
    <h1>Delete User Details</h1>

    <c:if test="${not empty message}">
        <p style="color: red;">${message}</p>
    </c:if>

    <form action="/admin/delete_user" method="post">
        <label for="email">Email:</label>
        <input type="email" name="email" required>
        <input type="submit" value="Delete">
    </form>



    <a href="/welcome">Welcome page</a>

</body>
</html>
