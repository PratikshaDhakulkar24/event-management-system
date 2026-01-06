<%
    String username = request.getParameter("username");
    String role = request.getParameter("role");

    if (username == null) username = "User";
    if (role == null) role = "Guest";
%>

<style>
.profile-box {
    max-width: 400px;
    background: #f2f2f2;
    padding: 25px;
    border-radius: 8px;
}
.profile-box h2 {
    text-align: center;
}
.profile-box p {
    font-size: 16px;
    margin: 12px 0;
}
.label {
    font-weight: bold;
}
</style>

<div class="profile-box">
    <h2>My Profile</h2>

    <p>
        <span class="label">Username:</span>
        <%= username %>
    </p>

    <p>
        <span class="label">Role:</span>
        <%= role %>
    </p>
</div>
