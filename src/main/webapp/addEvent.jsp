<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add New Event</title>
<link rel="stylesheet" href="style.css">
</head>
<body>

<div class="login-box">
    <h2>Add New Event</h2>
    <form action="EventServlet" method="post">
        <input type="hidden" name="action" value="add">

        <label>Event Name</label>
        <input type="text" name="name" required>

        <label>Event Date</label>
        <input type="date" name="date" required>

        <label>Venue</label>
        <input type="text" name="venue" required>

        <label>Capacity</label>
        <input type="number" name="capacity" min="1" required>

        <button type="submit">Add Event</button>
    </form>
</div>

</body>
</html>
