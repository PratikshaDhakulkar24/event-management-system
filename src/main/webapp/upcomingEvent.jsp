<%@ page import="java.util.*, dao.*, model.*, java.text.SimpleDateFormat" %>

<h2 class="section-title">Upcoming Events</h2>

<table class="events-table">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Date</th>
        <th>Venue</th>
        <th>Capacity</th>
    </tr>

<%
    EventDAO dao = new EventDAOImpl();
    List<Event> list = dao.getUpcomingEvents();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    if(list != null && !list.isEmpty()){
        for(Event e : list){
%>
<tr>
    <td><%= e.getId() %></td>
    <td><%= e.getName() %></td>
    <td>
        <%
            try {
                java.util.Date date = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(e.getDate());
                out.print(sdf.format(date));
            } catch(Exception ex) {
                out.print(e.getDate());
            }
        %>
    </td>
    <td><%= e.getVenue() %></td>
    <td><%= e.getCapacity() %></td>
</tr>
<%
        }
    } else {
%>
<tr>
    <td colspan="5" style="text-align:center;">No upcoming events</td>
</tr>
<%
    }
%>
</table>
