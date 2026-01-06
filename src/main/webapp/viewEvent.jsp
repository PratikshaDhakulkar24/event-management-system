<%@ page import="java.util.*" %>
<%@ page import="dao.EventDAO, dao.EventDAOImpl" %>
<%@ page import="model.Event" %>

<h2 class="section-title">All Events</h2>

<table class="events-table">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Date</th>
        <th>Venue</th>
        <th>Capacity</th>
        <th>Action</th>
    </tr>

<%
    EventDAO dao = new EventDAOImpl();
    List<Event> list = dao.getAllEvents();

    for(Event e : list){
%>
<tr>
    <td><%= e.getId() %></td>
    <td><%= e.getName() %></td>
    <td><%= e.getDate() %></td>
    <td><%= e.getVenue() %></td>
    <td><%= e.getCapacity() %></td>

    <td style="display:flex; gap:10px;">
        <a href="updateEvent.jsp?id=<%= e.getId() %>" class="apply-btn">Update</a>

        <form action="<%= request.getContextPath() %>/EventServlet" method="post"
              onsubmit="return confirm('Are you sure you want to delete this event?');">
            <input type="hidden" name="action" value="delete">
            <input type="hidden" name="id" value="<%= e.getId() %>">
            <button type="submit" class="apply-btn" style="background-color:#dc3545;">
                Delete
            </button>
        </form>
    </td>
</tr>
<%
    }
%>
</table>
