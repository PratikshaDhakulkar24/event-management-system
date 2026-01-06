<%@ page import="java.sql.*" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    if (session == null || session.getAttribute("email") == null) {
        response.sendRedirect("userlogin.jsp");
        return;
    }

    String email = (String) session.getAttribute("email");
%>

<h2>My Event Registrations</h2>

<table border="1" cellpadding="10" style="width:100%; border-collapse:collapse;">
    <tr style="background:#0059b3; color:white;">
        <th>Event Name</th>
        <th>Department</th>
        <th>Year</th>
        <th>Contact</th>
        <th>Gender</th>
    </tr>

<%
try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3307/campus_event_db",
        "root",
        "Vaishnavi@11102002"
    );

    PreparedStatement ps = con.prepareStatement(
        "SELECT event_name, department, year, contact, gender FROM registrations WHERE email=?"
    );
    ps.setString(1, email);

    ResultSet rs = ps.executeQuery();
    boolean found = false;

    while (rs.next()) {
        found = true;
%>
    <tr>
        <td><%= rs.getString("event_name") %></td>
        <td><%= rs.getString("department") %></td>
        <td><%= rs.getString("year") %></td>
        <td><%= rs.getString("contact") %></td>
        <td><%= rs.getString("gender") %></td>
    </tr>
<%
    }

    if (!found) {
%>
    <tr>
        <td colspan="5" style="text-align:center; color:red;">
            You have not registered for any events yet.
        </td>
    </tr>
<%
    }

    con.close();
} catch (Exception e) {
    out.println("<tr><td colspan='5' style='color:red'>" + e + "</td></tr>");
}
%>
</table>