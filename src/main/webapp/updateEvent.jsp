<%@ page import="dao.EventDAO,dao.EventDAOImpl,model.Event" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));
%>

<h2>Update Event</h2>

<form action="<%= request.getContextPath() %>/EventServlet" method="post">

    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="<%= id %>">

    <label>Event Name</label>
    <input type="text" name="name" required>

    <label>Date</label>
    <input type="date" name="date" required>

    <label>Venue</label>
    <input type="text" name="venue" required>

    <label>Capacity</label>
    <input type="number" name="capacity" required>

    <br><br>
    <button type="submit">Update Event</button>
</form>
