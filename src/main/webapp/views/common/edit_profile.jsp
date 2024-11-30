<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Password</title>

    <script type="text/javascript">

        function validatePassword() {
            var password = document.getElementById("password").value;
            var confirmPassword = document.getElementById("confirmPassword").value;

            if (password !== confirmPassword) {
                alert("Passwords do not match!");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <h1>Update Your Password</h1>

    <c:if test="${not empty message}">
        <p style="color: red;">${message}</p>
    </c:if>

    <!-- Form to update the password -->
    <form action="/update_password" method="post" onsubmit="return validatePassword()">

        <label for="currentPassword">Current Password:</label><br>
        <input type="password" id="currentPassword" name="currentPassword" required><br><br>

        <label for="password">New Password:</label><br>
        <input type="password" id="password" name="password" required><br><br>

        <label for="confirmPassword">Confirm Password:</label><br>
        <input type="password" id="confirmPassword" name="confirmPassword" required><br><br>

        <button type="submit">Update Password</button>
    </form>

    <br>
    <a href="/welcome">Welcome page</a>
</body>
</html>
