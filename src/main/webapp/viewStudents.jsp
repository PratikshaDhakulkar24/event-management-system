<%@ page import="java.util.*, model.EventRegistration" %>

<h2 style="color:#004080;">View All Students</h2>

<!-- ================= SEARCH BAR ================= -->
<form method="get"
      action="<%=request.getContextPath()%>/studentMaster"
      style="margin-bottom:20px; display:flex; gap:10px;">

    <input type="text"
           name="fullName"
           placeholder="Search by Name"
           value="<%= request.getParameter("fullName") != null ? request.getParameter("fullName") : "" %>"
           style="padding:8px;">

    <select name="department" style="padding:8px;">
        <option value="">All Departments</option>
        <option value="IT">IT</option>
        <option value="HR">HR</option>
        <option value="Marketing">Marketing</option>
        <option value="Finance">Finance</option>
    </select>

    <button type="submit" class="apply-btn">Search</button>

    <a href="<%=request.getContextPath()%>/studentMaster"
       class="apply-btn"
       style="background:#6c757d;">Reset</a>
</form>
<!-- ================= SEARCH BAR END ================= -->

<table class="events-table">
<tr>
    <th>ID</th>
    <th>Username</th>
    <th>Full Name</th>
    <th>Contact</th>
    <th>Department</th>
    <th>Event</th>
    <th>Gender</th>
    <th>Action</th>
</tr>

<%
List<EventRegistration> list =
        (List<EventRegistration>) request.getAttribute("studentList");

if (list == null) {
    list = new ArrayList<>();
}

String editId = request.getParameter("editId");

for (EventRegistration r : list) {
    boolean isEdit = editId != null && editId.equals(String.valueOf(r.getId()));
%>

<tr>
<% if (isEdit) { %>

<form method="post" action="<%=request.getContextPath()%>/updateStudent">
    <td>
        <%= r.getId() %>
        <input type="hidden" name="id" value="<%=r.getId()%>">
    </td>
    <td><%= r.getUsername() %></td>
    <td><input type="text" name="fullName" value="<%=r.getFullName()%>"></td>
    <td><input type="text" name="contact" value="<%=r.getContact()%>"></td>
    <td>
        <select name="department">
            <option <%=r.getDepartment().equals("IT")?"selected":""%>>IT</option>
            <option <%=r.getDepartment().equals("HR")?"selected":""%>>HR</option>
            <option <%=r.getDepartment().equals("Marketing")?"selected":""%>>Marketing</option>
            <option <%=r.getDepartment().equals("Finance")?"selected":""%>>Finance</option>
        </select>
    </td>
    <td><%= r.getEventName() %></td>
    <td><%= r.getGender() %></td>
    <td>
        <button class="save-btn">Save</button>
        <a href="<%=request.getContextPath()%>/studentMaster">Cancel</a>
    </td>
</form>

<% } else { %>

    <td><%= r.getId() %></td>
    <td><%= r.getUsername() %></td>
    <td><%= r.getFullName() %></td>
    <td><%= r.getContact() %></td>
    <td><%= r.getDepartment() %></td>
    <td><%= r.getEventName() %></td>
    <td><%= r.getGender() %></td>
    <td>
        <a class="apply-btn" style="flex: 1; text-align: center; padding: 8px 5px; background-color: #28a745; color: white; text-decoration: none; border-radius: 5px;" href="<%=request.getContextPath()%>/studentMaster?editId=<%=r.getId()%>">Update</a> |
        <a class="apply-btn" style="flex: 1; text-align: center; padding: 8px 5px; background-color: #E63E3E; color: white; text-decoration: none; border-radius: 5px;" href="<%=request.getContextPath()%>/deleteStudent?id=<%=r.getId()%>"
           onclick="return confirm('Are you sure?')">Delete</a>
    </td>

<% } %>
</tr>

<% } %>
</table>
