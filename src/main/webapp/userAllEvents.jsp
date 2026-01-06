<%@ page import="java.util.*, dao.EventDAO, dao.EventDAOImpl, model.Event, model.EventRegistration, dao.EventRegistrationDAOImpl" %>

<h2>All Events</h2>

<table class="events-table">
    <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Date</th>
            <th>Venue</th>
            <th>Capacity</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
    <%
        EventDAO dao = new EventDAOImpl();
        List<Event> list = dao.getAllEvents();
        String username = (String) session.getAttribute("username");

        List<EventRegistration> myRegs = new EventRegistrationDAOImpl().getRegistrationsByUsername(username);

        for(Event e : list){
            boolean registered = myRegs.stream().anyMatch(r -> r.getEventName().equals(e.getName()));
    %>
        <tr>
            <td><%= e.getId() %></td>
            <td><%= e.getName() %></td>
            <td><%= e.getDate() %></td>
            <td><%= e.getVenue() %></td>
            <td><%= e.getCapacity() %></td>
            <td>
               <form action="userdashboard.jsp" method="get">
                   <input type="hidden" name="eventName" value="<%= e.getName() %>">
                   <button type="submit" class="apply-btn" <%= registered ? "disabled" : "" %>>
                       <%= registered ? "Registered" : "Apply" %>
                   </button>
               </form>
            </td>
        </tr>
    <%
        }
    %>
    </tbody>
</table>
