<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
    HttpSession s = request.getSession(false);
    String username = (String) s.getAttribute("username");
%>

<div class="profile-card">
    <!-- Avatar Circle -->
    <div class="avatar">
        <%= username != null && username.length() > 0 ? username.substring(0,1).toUpperCase() : "U" %>
    </div>

    <h2>My Profile</h2>

    <table class="profile-table">
        <tr>
            <th>Username</th>
            <td><%= username %></td>
        </tr>
        <tr>
            <th>Role</th>
            <td>User</td>
        </tr>
    </table>
</div>