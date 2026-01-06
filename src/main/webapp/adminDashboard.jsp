<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>Admin Dashboard</title>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">

<style>
body {
    margin: 0;
    font-family: Arial, sans-serif;
    background-color: #f2f2f2;
}

/* NAVBAR */
.navbar {
    position: sticky;
    top: 0;
    z-index: 1000;
    background-color: #d9edf7;
    padding: 15px 40px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.logo {
    font-size: 26px;
    font-weight: bold;
    color: #004080;
}

.nav-links a {
    margin-left: 20px;
    text-decoration: none;
    font-weight: bold;
    color: #000;
}

/* DASHBOARD */
.dashboard {
    display: flex;
    min-height: calc(100vh - 70px);
}

/* SIDEBAR */
.sidebar {
    width: 260px;
    background-color: #bcdff1;
    padding: 20px;
}

.side-title {
    font-size: 18px;
    margin-bottom: 20px;
}

.side-btn {
    background-color: #0b5ed7;
    color: white;
    padding: 12px;
    margin-bottom: 12px;
    border-radius: 6px;
    cursor: pointer;
}

.side-btn:hover {
    background-color: #084298;
}

.sub-menu {
    display: none;
    margin-left: 10px;
}

.sub-menu a {
    display: block;
    background-color: #5fa8d3;
    color: white;
    padding: 10px;
    margin-bottom: 8px;
    text-decoration: none;
    border-radius: 6px;
}

.sub-menu a:hover {
    background-color: #3d7ea6;
}

/* CONTENT */
.content {
    flex: 1;
    padding: 30px;
    background-color: #ffffff;
}
</style>

<script>
function toggleMenu(menuId) {
    var menu = document.getElementById(menuId);
    menu.style.display = (menu.style.display === "block") ? "none" : "block";
}
</script>

</head>
<body>

<!-- NAVBAR -->
<div class="navbar">
    <div class="logo">CEMS</div>
    <div class="nav-links">
        <a href="index.jsp">Home</a>
        <a href="#">How It Works</a>
        <a href="#">Why Choose</a>
        <a href="adminlogin.jsp">Logout</a>
    </div>
</div>

<!-- DASHBOARD -->
<div class="dashboard">

    <!-- SIDEBAR -->
    <div class="sidebar">
        <div class="side-title">Admin Panel</div>

        <!-- EVENT MASTER -->
        <div class="side-btn" onclick="toggleMenu('eventMaster')">
            Event Master ▾
        </div>
        <div class="sub-menu" id="eventMaster">
            <a href="adminDashboard.jsp?page=addEvent">Add Event</a>
            <a href="adminDashboard.jsp?page=viewEvent">View Events</a>
            <a href="adminDashboard.jsp?page=upcomingEvent">Upcoming Events</a>
            <a href="DownloadAllEventReportServlet">Download Event Report</a>
        </div>

        <!-- STUDENT MASTER -->
        <div class="side-btn" onclick="toggleMenu('studentMaster')">
            Student Master ▾
        </div>
        <div class="sub-menu" id="studentMaster">
            <a href="<%= request.getContextPath() %>/studentMaster">
                View All Students
            </a>
           <a href="<%=request.getContextPath()%>/DownloadAllStudentReportServlet"
   style="padding:6px 12px; background-color:#004080; color:white; text-decoration:none; border-radius:5px; margin-bottom:10px; display:inline-block;">
   Download All Students Report
</a>

        </div>
    </div>

    <!-- CONTENT -->
    <div class="content">

<%
    // ✅ HANDLE BOTH PARAMETER & ATTRIBUTE
    String pageName = request.getParameter("page");
    if (pageName == null) {
        pageName = (String) request.getAttribute("page");
    }

    if ("addEvent".equals(pageName)) {
%>
        <jsp:include page="addEvent.jsp" />
<%
    } else if ("viewEvent".equals(pageName)) {
%>
        <jsp:include page="viewEvent.jsp" />
<%
    } else if ("upcomingEvent".equals(pageName)) {
%>
        <jsp:include page="upcomingEvent.jsp" />
<%
    } else if ("viewStudents".equals(pageName)) {
%>
        <jsp:include page="viewStudents.jsp" />
<%
    } else {
%>
        <h2>Admin Dashboard</h2>
        <p>Welcome Admin to Campus Event Management System</p>
<%
    }
%>

    </div>
</div>

</body>
</html>
