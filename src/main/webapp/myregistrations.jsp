<%@ page import="java.util.*, dao.EventRegistrationDAOImpl, model.EventRegistration" %>

<link rel="stylesheet" href="css/style.css">

<h2>My Registrations</h2>

<table class="events-table">
<tr>
    <th>ID</th>
    <th>Full Name</th>
    <th>Contact</th>
    <th>Department</th>
    <th>Event</th>
    <th>Gender</th>
</tr>

<%
String username = (String) session.getAttribute("username");
EventRegistrationDAOImpl dao = new EventRegistrationDAOImpl();
List<EventRegistration> list = dao.getRegistrationsByUsername(username);

if (list == null || list.isEmpty()) {
%>
<tr>
    <td colspan="6" style="text-align:center;">No registrations found</td>
</tr>
<%
} else {
    for (EventRegistration r : list) {
%>
<tr>
    <td><%= r.getId() %></td>
    <td><%= r.getFullName() %></td>
    <td><%= r.getContact() %></td>
    <td><%= r.getDepartment() %></td>
    <td><%= r.getEventName() %></td>
    <td><%= r.getGender() %></td>
</tr>
<%
    }
}
%>
</table>
