<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Login</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="login-box">
    <h2>Login</h2>

    <% String error = (String) request.getAttribute("error"); 
       if(error != null) { %>
       <p style="color:red;"><%= error %></p>
    <% } %>

    <form action="adminlogin" method="post">
    <input type="text" placeholder="Enter UserName" name="id" required>
    <input type="password" placeholder="Enter Password"name="pass" required>
    <button type="submit">Login</button>
</form>

</div>

</body>
</html>
