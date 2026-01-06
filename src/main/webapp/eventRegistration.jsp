<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.EventDAO, dao.EventDAOImpl" %>
<%@ page import="model.Event" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Event Registration</title>
<link rel="stylesheet" href="css/style.css">


</head>
<body>

<%
    // Event Name only
    String eventName = request.getParameter("eventName") != null ? request.getParameter("eventName") : "";
%>

<div class="events-table" style="max-width:600px; margin:50px auto; padding:20px;">
    <h2 style="text-align:center; color:#004080;">Event Registration</h2>
   <form id="registrationForm"
      action="<%= request.getContextPath() %>/RegisterServlet"
      method="post">


        <input type="hidden" name="event" value="<%= eventName %>">

        <input type="text" name="fullName" placeholder="Full Name" required>
        <input type="text" name="contact" placeholder="Contact Number" required>

        <select name="department" required>
            <option value="">Select Department</option>
            <option value="IT">IT</option>
            <option value="HR">HR</option>
            <option value="Marketing">Marketing</option>
            <option value="Finance">Finance</option>
        </select>

        <input type="text" name="eventName" placeholder="Event Name" value="<%= eventName %>" readonly>

        <select name="gender" required>
            <option value="">Select Gender</option>
            <option value="Male">Male</option>
            <option value="Female">Female</option>
            <option value="Other">Other</option>
        </select>

        <button class="apply-btn" type="submit">Submit</button>
    </form>
</div>

</body>
</html>
