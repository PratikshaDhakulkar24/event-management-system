<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Delete Event</h2>

<form action="EventServlet" method="post">
    <input type="hidden" name="action" value="delete">

    <label>Event ID</label>
    <input type="number" name="id" required>

    <button type="submit">Delete Event</button>
</form>


</body>
</html>