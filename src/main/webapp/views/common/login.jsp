<%@ page language = "java" contentType = "text/html; charset = UTF-8"
    pageEncoding = "UTF-8" isELIgnored = "false"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset = "UTF-8">
    <title>Login</title>
</head>
<body>
    <h2>Login Form</h2>
    ${SPRING_SECURITY_LAST_EXCEPTION.message}
    <a href="/oauth2/authorization/google">Login with Google</a><br>
    <form action="login" method="post">
        <input type="text" name="username" placeholder="Username" required>
        <input type="password" name="password" placeholder="Password" required>
        <button type="submit">Login</button>

    </form>

</body>
</html>