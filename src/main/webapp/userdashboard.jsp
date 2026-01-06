<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<title>User Dashboard</title>
<link rel="stylesheet" href="css/style.css">

<style>
body { margin:0; font-family: Arial,sans-serif; background-color:#f2f2f2; }

.navbar {
    position: sticky;
    top:0;
    background-color:#d9edf7;
    padding:15px 40px;
    display:flex;
    justify-content:space-between;
}

.logo { font-size:26px; font-weight:bold; color:#004080; }

.nav-links a {
    margin-left:20px;
    text-decoration:none;
    font-weight:bold;
    color:#000;
}

.dashboard { display:flex; min-height:calc(100vh - 70px); }

.sidebar {
    width:260px;
    background-color:#bcdff1;
    padding:20px;
}

.side-title {
    font-size:18px;
    margin-bottom:20px;
}

.side-btn {
    display:block;
    background-color:#0b5ed7;
    color:white;
    padding:12px;
    margin-bottom:12px;
    text-decoration:none;
    border-radius:6px;
}

.side-btn:hover { background-color:#084298; }

.content {
    flex:1;
    padding:30px;
    background-color:#ffffff;
}

/* Profile */
.profile-box {
    max-width:400px;
    margin:auto;
    background:#fff;
    padding:25px;
    border-radius:12px;
    box-shadow:0 4px 8px rgba(0,0,0,0.1);
    text-align:center;
}

.profile-circle {
    width:100px;
    height:100px;
    border-radius:50%;
    background:#0b5ed7;
    color:white;
    display:flex;
    align-items:center;
    justify-content:center;
    font-size:40px;
    margin:auto;
}

/* Registration */
.registration-box {
    max-width:600px;
    margin:auto;
    padding:20px;
}

.registration-box input,
.registration-box select {
    width:100%;
    padding:10px;
    margin:10px 0;
    border-radius:6px;
    border:1px solid #ccc;
}

.submit-btn {
    padding:10px 20px;
    background-color:#28a745;
    border:none;
    border-radius:5px;
    color:white;
    cursor:pointer;
}

.submit-btn:hover {
    background-color:#218838;
}
</style>
</head>

<body>

<%
String username = (String) session.getAttribute("username");
if (username == null) {
    response.sendRedirect("userlogin.jsp");
    return;
}

String view = request.getParameter("view");
String eventName = request.getParameter("eventName");
%>

<!-- NAVBAR -->
<div class="navbar">
    <div class="logo">CEMS</div>
    <div class="nav-links">
        <a href="index.jsp">Home</a>
        <a href="userlogin.jsp">Logout</a>
    </div>
</div>

<div class="dashboard">

    <!-- SIDEBAR -->
    <div class="sidebar">
        <div class="side-title">User Menu</div>

        <a href="userdashboard.jsp?view=profile" class="side-btn">My Profile</a>
        <a href="userdashboard.jsp?view=events" class="side-btn">All Events</a>
        <a href="userdashboard.jsp?view=myregistrations" class="side-btn">My Registrations</a>
<a href="<%= request.getContextPath() %>/DownloadUserPDF" class="side-btn">
    Download PDF Report
</a>

    </div>

    <!-- CONTENT -->
    <div class="content">

<%
if ("profile".equals(view)) {
%>

        <div class="profile-box">
            <div class="profile-circle">
                <%= username.substring(0,1).toUpperCase() %>
            </div>
            <h2>My Profile</h2>
            <p><b>Username:</b> <%= username %></p>
            <p><b>Role:</b> User</p>
        </div>

<%
} else if ("events".equals(view)) {
%>

        <jsp:include page="userAllEvents.jsp" />

<%
} else if (eventName != null) {
%>

        <!-- EVENT REGISTRATION FORM -->
        <div class="registration-box">
            <h2 style="text-align:center;color:#004080;">Event Registration</h2>

            <form action="<%= request.getContextPath() %>/RegisterServlet" method="post">

                <input type="hidden" name="eventName" value="<%= eventName %>">

                <input type="text" name="fullName" placeholder="Full Name" required>

                <input type="text" name="contact" placeholder="Contact Number" required>

                <select name="department" required>
                    <option value="">Select Department</option>
                    <option value="IT">IT</option>
                    <option value="CS">CS</option>
                    <option value="Mechanical">Mechanical</option>
                    <option value="Civil">Civil</option>
                    <option value="Electrical">Electrical</option>
                </select>

                <input type="text" value="<%= eventName %>" readonly>

                <select name="gender" required>
                    <option value="">Select Gender</option>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Other">Other</option>
                </select>

                <button type="submit" class="submit-btn">Submit</button>
            </form>
        </div>

<%
} else if ("myregistrations".equals(view)) {
%>

        <jsp:include page="myregistrations.jsp" />

<%
} else if ("download".equals(view)) {
%>

        <div style="text-align:center;">
            <h2>Download Registration Report</h2>
            <p>Click below to download your registrations</p>

            <a href="<%= request.getContextPath() %>/DownloadUserReport">
                <button class="submit-btn">Download CSV Report</button>
            </a>
        </div>

<%
} else {
%>

        <h2>Welcome <%= username %></h2>
        <p>Select an option from the left menu</p>

<%
}
%>

    </div>
</div>

</body>
</html>
