<%@ page language = "java" contentType = "text/html; charset = UTF-8"
    pageEncoding = "UTF-8" isELIgnored = "false"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset = "UTF-8">
    <title>Welcome</title>
</head>
<body>
    <h1>Welcome to the User Management System</h1>
        <p>Please choose an action:</p>

        <div class="button-container">

            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <h3>admin</h2>
                <a href="/admin/create_user" class="btn">Create User</a> <br/>
                <a href="/admin/update_user" class="btn">Update User Details</a> <br/>
                <a href="/admin/list_user" class="btn">List, Delete, View User Details</a> <br/>
            </sec:authorize>

                <a href="/update_password" class="btn">Update profile</a> <br/>
                <a href="/view_details" class="btn">View Details</a></><br/>

            <a href="/logout" class="btn">Logout</a>

        </div>
</body>
</html>